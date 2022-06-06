package top.jonhao.cm.publish.task

import top.jonhao.cm.publish.constant.DocumentType
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.jvm.tasks.Jar

open class JavadocJar : Jar() {

    init {
        archiveClassifier.set("javadoc")
    }

    companion object {
        fun Project.javadocJar(
            documentType: DocumentType,
            android: Boolean = false
        ): TaskProvider<*> {
            return when (documentType) {
                DocumentType.Empty -> emptyJavadocJar()
                DocumentType.Javadoc -> plainJavadocJar(android)
                DocumentType.Dokka -> dokkaJavadocJar()
            }
        }

        private fun Project.emptyJavadocJar(): TaskProvider<*> =
            tasks.register("emptyJavadocJar", JavadocJar::class.java)

        private fun Project.plainJavadocJar(android: Boolean): TaskProvider<*> {
            return if (android) {
                val androidJavadoc = tasks.register("androidJavadoc", AndroidJavadocs::class.java)
                tasks.register("androidJavadocJar", JavadocJar::class.java) {
                    dependsOn(androidJavadoc)
                    from(androidJavadoc)
                }
            } else {
                tasks.register("simpleJavadocJar", JavadocJar::class.java) {
                    val task = tasks.named("javadoc")
                    dependsOn(task)
                    from(task)
                }
            }
        }

        private fun Project.dokkaJavadocJar(): TaskProvider<*> {
            return tasks.register("dokkaJavadocJar", JavadocJar::class.java) {
                val task = tasks.named("dokka")
                dependsOn(task)
                from(task)
            }
        }
    }
}
