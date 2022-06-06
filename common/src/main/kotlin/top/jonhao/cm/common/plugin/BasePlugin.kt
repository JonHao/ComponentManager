package top.jonhao.cm.common.plugin

import PublicPlugins
import top.jonhao.cm.common.extension.useKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BasePlugin : Plugin<Project> {

    abstract val plugins: Array<String>

    override fun apply(project: Project) {
        applyPlugin(project)
    }

    private fun applyPlugin(project: Project) {
        val useKotlin = project.useKotlin
        plugins.forEach label@{
            if ((it == PublicPlugins.kotlinAndroid || it == PublicPlugins.kotlinJvm) && !useKotlin) {
                return@label
            }
            project.apply { plugin(it) }
        }
    }
}