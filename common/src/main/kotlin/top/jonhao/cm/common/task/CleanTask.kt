package top.jonhao.cm.common.task

import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.TaskAction

abstract class CleanTask : Delete() {

    @TaskAction
    fun execute() {
        project.rootProject.buildDir.apply {
            delete(this)
        }
        super.clean()
    }
}