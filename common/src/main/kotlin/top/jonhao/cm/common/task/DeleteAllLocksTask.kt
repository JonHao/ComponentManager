package top.jonhao.cm.common.task

import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DeleteAllLocksTask: Delete() {

    @TaskAction
    override fun clean() {
        val lockFiles = mutableListOf<File>()
        project.allprojects.forEach { project ->
            project.projectDir.listFiles()?.filter {
                it.name.endsWith("gradle.lockfile")
            }?.apply {
                lockFiles.addAll(this)
            }
        }
        setDelete(lockFiles)
        super.clean()
    }
}