package top.jonhao.cm.base.tool

import org.gradle.process.ExecSpec
import java.io.File
import java.util.*

object BaseUtils {

    val systemName = System.getProperty("os.name").toLowerCase(Locale.ROOT)

    fun isWindows(): Boolean {
        return systemName.contains("windows")
    }

    fun commandLine(exec: ExecSpec, command: String) {
        exec.apply {
            if (isWindows()) {
                commandLine("cmd", "/c", command)
            } else {
                commandLine("sh", "-c", command)
            }
        }
    }

    fun gradleCommandLine(exec: ExecSpec, rootDir: String, command: String) {
        commandLine(exec, "${rootDir}${File.separator}gradlew $command")
    }

    inline fun <reified T> checkValue(value: Any?, defaultValue: T): T {
        return value?.run {
            try {
                T::class.java.cast(this)
            } catch (e: Throwable) {
                defaultValue
            }
        } ?: run {
            defaultValue
        }
    }
}