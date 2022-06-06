package top.jonhao.cm.publish.task

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.TaskProvider
import org.gradle.jvm.tasks.Jar

open class SourcesJar : Jar() {

    init {
        archiveClassifier.set("sources")
    }

    companion object {
        fun Project.emptySourcesJar(): TaskProvider<*> =
            tasks.register("emptySourcesJar", SourcesJar::class.java)

        fun Project.androidSourcesJar(sourcesJar: Boolean): TaskProvider<*> {
            if (!sourcesJar) {
                return emptySourcesJar()
            }

            return tasks.register("androidSourcesJar", SourcesJar::class.java) {
                val libraryExtension = project.extensions.getByType(LibraryExtension::class.java)
                from(libraryExtension.sourceSets.getByName("main").java.srcDirs)
            }
        }

        fun Project.javaSourcesJar(sourcesJar: Boolean): TaskProvider<*> {
            if (!sourcesJar) {
                return emptySourcesJar()
            }

            return tasks.register("javaSourcesJar", SourcesJar::class.java) {
                val javaPluginExtension =
                    project.extensions.getByType(JavaPluginExtension::class.java)
                from(javaPluginExtension.sourceSets.getByName("main").allSource)
            }
        }

        fun Project.kotlinSourcesJar(sourcesJar: Boolean): TaskProvider<*> {
            if (!sourcesJar) {
                return emptySourcesJar()
            }

            return tasks.named("kotlinSourcesJar")
        }
    }
}
