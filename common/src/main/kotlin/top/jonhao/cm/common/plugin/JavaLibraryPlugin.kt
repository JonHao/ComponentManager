package top.jonhao.cm.common.plugin

import PrivatePlugins
import PublicLibraries
import PublicPlugins
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import top.jonhao.cm.common.exception.NotUseLockException
import top.jonhao.cm.common.extension.fileTree
import top.jonhao.cm.common.extension.useJvmDefault
import top.jonhao.cm.common.extension.useKotlin
import top.jonhao.cm.common.tool.KotlinOptionsUtils
import top.jonhao.cm.publish.Publish
import top.jonhao.cm.publish.constant.DocumentType
import top.jonhao.cm.publish.entity.PublicationConfig
import top.jonhao.cm.publish.extension.useLock
import top.jonhao.cm.publish.extension.withDocument
import top.jonhao.cm.publish.extension.withSource
import top.jonhao.cm.publish.task.JavadocJar.Companion.javadocJar
import top.jonhao.cm.publish.task.SourcesJar.Companion.javaSourcesJar

class JavaLibraryPlugin : BasePlugin() {
    override val plugins: Array<String>
        get() = arrayOf(PublicPlugins.javaLibrary, PublicPlugins.kotlinJvm, PrivatePlugins.publish)

    override fun apply(project: Project) {
        super.apply(project)

        project.extensions.getByType(JavaPluginExtension::class.java).run {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        project.tasks.named<KotlinCompile>("compileKotlin") {
            KotlinOptionsUtils.configKotlinOptions(kotlinOptions, project.useJvmDefault)
        }

        Publish.setPublicationConfig(project, PublicationConfig().apply {
            if (project.withSource) {
                sourcesJar = project.javaSourcesJar(project.withSource).get()
            }
            if (project.withDocument) {
                javadocJar = project.javadocJar(DocumentType.Dokka, true).get()
            }
        })

        project.dependencies.apply {
            add("api", project.fileTree)
            if (project.useKotlin) {
                add("api", PublicLibraries.Kotlin.stdlib)
                add("testImplementation", PublicLibraries.UnitTest.junit)
            }
        }

        project.afterEvaluate {
            tasks.onEach label@{
                if (it.group == "publishing" && it.name.contains("publish")) {
                    it.doFirst {
                        if (!project.useLock) {
                            throw NotUseLockException()
                        }
                    }
                    it.dependsOn("assemble")
                }
            }
        }
    }
}