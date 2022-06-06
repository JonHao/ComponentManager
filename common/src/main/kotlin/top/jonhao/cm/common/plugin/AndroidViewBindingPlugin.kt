package top.jonhao.cm.common.plugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

@Suppress("UnstableApiUsage")
open class AndroidViewBindingPlugin : AndroidLibraryPlugin() {

    override fun apply(project: Project) {
        super.apply(project)
        val libraryExtension = project.extensions.getByType(LibraryExtension::class.java)

        libraryExtension.apply {
            buildFeatures.apply {
                dataBinding = false
                viewBinding = true
            }
        }
    }
}