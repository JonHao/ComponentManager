package top.jonhao.cm.common.task

import org.gradle.api.tasks.Exec
import top.jonhao.cm.base.tool.BaseUtils
import java.io.File

open class GenerateAllLocksTask : Exec() {

    override fun exec() {
        val command = StringBuilder()
        project.rootProject.allprojects {
            val projectName = if (this.project.name == this.project.rootProject.name) {
                ""
            } else {
                displayName.split('\'')[1]
            }
            command.append("${rootDir}${File.separator}gradlew ${projectName}:dependencies --write-locks;")
        }
        BaseUtils.commandLine(this@GenerateAllLocksTask, command.toString())
        super.exec()
    }
}