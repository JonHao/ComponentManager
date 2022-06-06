package top.jonhao.cm.publish.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import top.jonhao.cm.publish.Publish
import top.jonhao.cm.publish.constant.PublishConstants

@Suppress("UNCHECKED_CAST")
internal inline fun <reified T> Project.findGlobalProperty(propertyName: String): T? {
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
internal inline fun <reified T> Project.findGlobalProperty(propertyName: String, default: T): T {
    return findGlobalProperty(propertyName) ?: default
}

val Project.groupId: String
    get() = findGlobalProperty(PublishConstants.PROPERTY_KEY_GROUP, group.toString())

val Project.versionName: String
    get() = findGlobalProperty(PublishConstants.PROPERTY_KEY_VERSION, version.toString())

val Project.versionCode: Int
    get() = findGlobalProperty(PublishConstants.PROPERTY_KEY_VERSION_CODE, 1)

val Project.withDocument: Boolean
    get() = findGlobalProperty(
        PublishConstants.PROPERTY_KEY_WITH_DOCUMENT,
        PublishConstants.DEFAULT_WITH_DOCUMENT
    )

val Project.withSource: Boolean
    get() = findGlobalProperty(
        PublishConstants.PROPERTY_KEY_WITH_SOURCE,
        PublishConstants.DEFAULT_WITH_SOURCE
    )

val Project.useLock: Boolean
    get() = Publish.useLock || findGlobalProperty(
        PublishConstants.PROPERTY_KEY_USE_LOCK,
        PublishConstants.DEFAULT_USE_LOCK
    )