package top.jonhao.cm.common.task

import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import top.jonhao.cm.base.tool.FileUtils
import top.jonhao.cm.publish.constant.PublishConstants
import java.io.File

abstract class CleanGradleCacheTask : Delete() {

    private var groupId: String =
        this.project.findProperty(PublishConstants.PROPERTY_KEY_GROUP)
            .toString()

    @Option(
        option = "groupId",
        description = "Group id of this project."
    )
    fun setGroupId(groupId: String) {
        this.groupId = groupId
    }

    @Input
    fun getGroupId(): String {
        return this.groupId
    }

    @TaskAction
    fun execute() {
        println("${this.project.name} clean groupId : $groupId")
        project.rootProject.gradle.gradleUserHomeDir.absolutePath.apply {
            FileUtils.buildPath(this, "caches", "modules-2").apply {
                val pathArray = mutableListOf<String>()
                fun addPath(path: String) {
                    if (File(path).exists()) {
                        pathArray.add(path)
                    }
                }
                File(this)
                    .listFiles { _, name -> name.contains("metadata") }?.onEach {
                        FileUtils.buildPath(it.path, "descriptors", groupId)
                            .apply {
                                addPath(this)
                            }
                    }
                FileUtils.buildPath(this, "files-2.1", groupId).apply {
                    addPath(this)
                }

                delete(pathArray)

                pathArray.onEach {
                    println("Delete : $it")
                }
            }
        }
        super.clean()
    }
}