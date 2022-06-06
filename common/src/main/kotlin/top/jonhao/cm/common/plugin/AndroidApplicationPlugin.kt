package top.jonhao.cm.common.plugin

import PublicLibraries
import PublicPlugins
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import top.jonhao.cm.base.extension.escape
import top.jonhao.cm.base.extension.findStringProperty
import top.jonhao.cm.base.tool.GitUtils
import top.jonhao.cm.common.constant.CommonConstants
import top.jonhao.cm.common.extension.*
import top.jonhao.cm.common.tool.CommonUtils
import top.jonhao.cm.common.tool.KotlinOptionsUtils
import top.jonhao.cm.common.tool.UnitTestUtils
import top.jonhao.cm.publish.constant.PublishConstants
import top.jonhao.cm.publish.extension.versionCode
import top.jonhao.cm.publish.extension.versionName
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import java.io.File
import java.util.*

@Suppress("UnstableApiUsage")
class AndroidApplicationPlugin : BasePlugin() {

    override val plugins: Array<String>
        get() = arrayOf(
            PublicPlugins.application,
            PublicPlugins.kotlinAndroid
        )

    override fun apply(project: Project) {
        super.apply(project)
        UnitTestUtils.apply(project)

        val applicationExtension = project.extensions.getByType(BaseAppModuleExtension::class.java)

        applicationExtension.apply {
            compileSdk = project.compileSdk

            defaultConfig {
                minSdk = project.minSdk
                targetSdk = project.targetSdk

                applicationId = project.applicationId
                versionCode = project.versionCode
                versionName = project.versionName

                vectorDrawables.useSupportLibrary = true

                ndk.apply {
                    abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a"))
                }
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

            buildFeatures {
                viewBinding = true
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

            val storeFile = if (project.storeFile == CommonConstants.DEFAULT_SIGNING_STORE_FILE) {
                CommonUtils.getBackupResourceFile(
                    File("").canonicalPath + "/.gradle",
                    CommonConstants.DEFAULT_SIGNING_STORE_FILE
                )
            } else {
                project.file(project.storeFile)
            }

            signingConfigs.apply {
                getByName("debug") {
                    keyAlias = project.keyAlias
                    keyPassword = project.keyPassword
                    this.storeFile = storeFile
                    storePassword = project.storePassword
                    isV1SigningEnabled = true
                    isV2SigningEnabled = true
                }

                getByName("release") {
                    keyAlias = project.keyAlias
                    keyPassword = project.keyPassword
                    this.storeFile = storeFile
                    storePassword = project.storePassword
                    isV1SigningEnabled = true
                    isV2SigningEnabled = true
                }
            }

            applicationVariants.all {
                outputs.all label@{
                    if (this !is ApkVariantOutputImpl) {
                        return@label
                    }
                    productFlavors.onEach { flavor ->
                        var versionName: String = project.versionName
                        var versionCode: Int = project.versionCode
                        flavor.versionName?.apply { versionName = this }
                        flavor.versionCode?.apply { versionCode = this }

                        val moduleName = project.findStringProperty(
                            PublishConstants.PROPERTY_KEY_NAME,
                            project.name
                        )
                        outputFileName =
                            if (buildType.name.toLowerCase(Locale.ENGLISH) == "debug") {
                                "${moduleName}_${versionName}_${versionCode}_${flavor.name}_debug.apk"
                            } else {
                                "${moduleName}_${versionName}_${versionCode}_${flavor.name}_${CommonUtils.getReleaseTime()}.apk"
                            }
                    }
                }
            }

            productFlavors.apply {
                all {
                    buildConfigField(
                        "String",
                        "RELEASE_TIME",
                        CommonUtils.getReleaseTime().escape()
                    )
                    buildConfigField(
                        "String",
                        "BRANCH_INFO",
                        GitUtils.getBranchInfo(project).escape()
                    )
                    dimension = CommonConstants.PROJECT_NAME
                }
            }
        }

        project.dependencies.apply {
            add("implementation", project.fileTree)
            if (project.useKotlin) {
                add("implementation", PublicLibraries.Kotlin.stdlib)
            }
        }
    }
}