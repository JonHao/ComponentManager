import org.gradle.api.Project

object ComponentManager {

    object PublicLibraries {
        const val agp = "com.android.tools.build:gradle:4.1.0"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
        const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:1.5.31"
        const val commonIO = "commons-io:commons-io:2.11.0"
    }

    object PublicPlugins {
        const val application = "com.android.application"
        const val androidLibrary = "com.android.library"

        const val javaLibrary = "org.gradle.java-library"
        const val kotlinJvm = "org.jetbrains.kotlin.jvm"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val kotlinParcelize = "kotlin-parcelize"

        const val mavenPublish = "org.gradle.maven-publish"
        const val dokka = "org.jetbrains.dokka"
    }

    object PrivatePlugins {
        const val androidApplication = "cm.android.application"
        const val androidLibrary = "cm.android.library"
        const val javaLibrary = "cm.java.library"
        const val project = "cm.project"
        const val publish = "cm.publish"
    }

    fun fileTree(project: Project) =
        project.fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar")))

    var usePublish = true
}