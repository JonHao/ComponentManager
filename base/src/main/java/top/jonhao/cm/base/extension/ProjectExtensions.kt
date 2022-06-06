package top.jonhao.cm.base.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.findStringProperty(propertyName: String, default: String = ""): String =
    findProperty(propertyName)?.toString() ?: default

fun Project.findIntProperty(propertyName: String, default: Int = 0): Int {
    findProperty(propertyName)?.apply {
        return this.toString().toInt()
    }
    return default
}

fun Project.findBooleanProperty(propertyName: String, default: Boolean = false): Boolean {
    findProperty(propertyName)?.apply {
        return this.toString().toBoolean()
    }
    return default
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Project.findGlobalProperty(propertyName: String): T? {
    findProperty(propertyName)?.apply {
        return this as T
    }

    try {
        rootProject.extra.get(propertyName)?.run {
            return this as T
        }
    } catch (ignore: Exception) {
        return null
    }
    return null
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Project.findGlobalProperty(propertyName: String, default: T): T {
    return findGlobalProperty(propertyName) ?: default
}