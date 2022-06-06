package top.jonhao.cm.common.plugin

import PrivatePlugins
import PublicLibraries
import PublicPlugins
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.register
import top.jonhao.cm.common.constant.CommonConstants
import top.jonhao.cm.common.exception.NotUseLockException
import top.jonhao.cm.common.extension.*
import top.jonhao.cm.common.tool.KotlinOptionsUtils
import top.jonhao.cm.common.tool.UnitTestUtils
import top.jonhao.cm.publish.Publish
import top.jonhao.cm.publish.constant.DocumentType
import top.jonhao.cm.publish.entity.PublicationConfig
import top.jonhao.cm.publish.extension.useLock
import top.jonhao.cm.publish.extension.withDocument
import top.jonhao.cm.publish.extension.withSource
import top.jonhao.cm.publish.task.JavadocJar.Companion.javadocJar
import top.jonhao.cm.publish.task.SourcesJar.Companion.androidSourcesJar
import java.util.*

@Suppress("UnstableApiUsage")
open class AndroidLibraryPlugin : BasePlugin() {

    override val plugins: Array<String>
        get() = arrayOf(
            PublicPlugins.androidLibrary,
            PublicPlugins.kotlinAndroid,
            PrivatePlugins.publish
        )

    private var releaseOutputFilePath: String? = null

    override fun apply(project: Project) {
        super.apply(project)
        UnitTestUtils.apply(project)

        val libraryExtension = project.extensions.getByType(LibraryExtension::class.java)

        libraryExtension.run {
            compileSdk = project.compileSdk

            defaultConfig {
                minSdk = project.minSdk
                targetSdk = project.targetSdk

                vectorDrawables.useSupportLibrary = true

                ndk {
                    abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
                }
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            if (project.useKotlin) {
                KotlinOptionsUtils.configKotlinOptions(
                    this as ExtensionAware,
                    "kotlinOptions",
                    project.useJvmDefault
                )
            }

            buildFeatures.apply {
                viewBinding = false
                dataBinding = false
            }

            buildTypes.apply {
                getByName("debug") {
                    isDebuggable = true
                    isMinifyEnabled = false
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt")
                    )
                    signingConfig = signingConfigs.maybeCreate("debug")
                }

                getByName("release") {
                    isDebuggable = false
                    isMinifyEnabled = false
                    isShrinkResources = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt")
                    )
                    signingConfig = signingConfigs.maybeCreate("release")
                }
            }

            libraryVariants.all {
                if (buildType.name.toLowerCase(Locale.getDefault()) == "release") {
                    packageLibraryProvider.get().apply label@{
                        if (!isEnabled) {
                            return@label
                        }
                        releaseOutputFilePath = archiveFile.get().asFile.path
                        Publish.setPublicationConfig(project, PublicationConfig().apply {
                            outputFilePath = releaseOutputFilePath
                            if (project.withSource) {
                                sourcesJar = project.androidSourcesJar(project.withSource).get()
                            }
                            if (project.withDocument) {
                                javadocJar = project.javadocJar(DocumentType.Dokka, true).get()
                            }
                        })
                    }
                }
            }

            project.afterEvaluate {
                releaseOutputFilePath?.let { filePath ->
                    project.tasks.register<Copy>("exportAar") {
                        libraryVariants.all {
                            if (buildType.name.toLowerCase(Locale.getDefault()) == "release") {
                                from(filePath)
                                into("${project.rootDir}/../output/")
                            }
                        }
                    }
                }
            }
        }

        project.dependencies.apply {
            add("api", project.fileTree)
            if (project.useKotlin) {
                add("api", PublicLibraries.Kotlin.stdlib)
            }
        }

        project.afterEvaluate {
            tasks.onEach label@{
                if (it.name == "exportAar") {
                    it.group = CommonConstants.PROJECT_NAME
                    it.dependsOn("bundleReleaseAar")
                    return@label
                }
                if (it.group == "publishing" && it.name.contains("publish")) {
                    it.doFirst {
                        if (!project.useLock) {
                            throw NotUseLockException()
                        }
                    }
                    it.dependsOn("bundleReleaseAar")
                }
            }
        }
    }
}