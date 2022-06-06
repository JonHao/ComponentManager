package top.jonhao.cm.base.tool

import java.io.File
import java.util.*

object FileUtils {
    fun buildPath(vararg args: String): String {
        val size = args.size
        val path = StringBuilder()
        for (i in 0 until size) {
            path.append(args[i])
            if (i != size - 1) {
                path.append(File.separator)
            }
        }
        return path.toString()
    }

    fun getProperties(propertiesFilePath: String): Properties? {
        val properties = Properties()
        val propertiesFile = File(propertiesFilePath)
        return if (propertiesFile.exists()) {
            propertiesFile.reader(java.nio.charset.Charset.forName("UTF-8")).run {
                properties.load(this)
            }
            properties
        } else {
            null
        }
    }

    fun deleteDirectory(directory: File): Boolean {
        directory.list()?.onEach {
            val result = File(it).delete()
            if (!result) {
                return false
            }
        }
        return true
    }

    fun deleteFile(file: File): Boolean {
        return if (file.isDirectory) {
            deleteDirectory(file)
        } else {
            file.delete()
        }
    }
}