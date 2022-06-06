package top.jonhao.cm.common.tool

import org.apache.commons.io.FileUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

internal object CommonUtils {

    /**
     * 获取打包时间
     *
     * @return String 打包时间
     */
    fun getReleaseTime(): String {
        return SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
    }

    /**
     * 获取备份的资源文件
     *
     * @param destinationDir String 目标路径
     * @param resourceFileName String 资源文件名
     * @return File
     */
    fun getBackupResourceFile(destinationDir: String, resourceFileName: String): File {
        val file = File(destinationDir, resourceFileName)
        if (file.exists()) {
            return file
        }
        val inputStream = this::class.java.getResourceAsStream("/$resourceFileName")
        inputStream?.apply {
            val byteArray = this.readBytes()
            FileUtils.writeByteArrayToFile(file, byteArray)
        }
        return file
    }
}