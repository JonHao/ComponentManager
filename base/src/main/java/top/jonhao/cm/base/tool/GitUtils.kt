package top.jonhao.cm.base.tool

import org.gradle.api.Project
import java.io.File
import java.io.FileReader

object GitUtils {
    fun getBranchInfo(project: Project): String {
        val result = ""
        val file = File("${project.rootDir}/.git/HEAD")
        if (!file.exists()) {
            return result
        }
        var fileReader: FileReader? = null
        try {
            fileReader = FileReader(file).apply {
                forEachLine {
                    result.plus(it)
                }
            }
        } catch (e: Exception) {
            return e.javaClass.name + ":" + e.message + ":" + file.absolutePath
        } finally {
            fileReader?.run {
                try {
                    this.close()
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
        return result
    }
}