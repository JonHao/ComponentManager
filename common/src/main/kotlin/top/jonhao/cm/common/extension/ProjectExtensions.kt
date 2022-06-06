package top.jonhao.cm.common.extension

import PublicVersions
import org.gradle.api.Project
import top.jonhao.cm.base.extension.findBooleanProperty
import top.jonhao.cm.base.extension.findGlobalProperty
import top.jonhao.cm.base.extension.findStringProperty
import top.jonhao.cm.common.constant.CommonConstants
import top.jonhao.cm.publish.extension.groupId

val Project.fileTree get() = fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar")))

val Project.compileSdk: Int
    get() = findGlobalProperty(
        CommonConstants.PROPERTY_KEY_COMPILE_SDK,
        PublicVersions.Android.compileSdk
    )

val Project.minSdk: Int
    get() = findGlobalProperty(CommonConstants.PROPERTY_KEY_MIN_SDK, PublicVersions.Android.minSdk)

val Project.targetSdk: Int
    get() = findGlobalProperty(
        CommonConstants.PROPERTY_KEY_TARGET_SDK,
        PublicVersions.Android.targetSdk
    )

val Project.applicationId: String
    get() = findStringProperty(CommonConstants.PROPERTY_KEY_APPLICATION_ID, groupId)

val Project.useKotlin: Boolean
    get() = findBooleanProperty(
        CommonConstants.PROPERTY_KEY_USE_KOTLIN,
        CommonConstants.DEFAULT_USE_KOTLIN
    )

val Project.useJvmDefault: Boolean
    get() = findBooleanProperty(
        CommonConstants.PROPERTY_KEY_USE_JVM_DEFAULT,
        CommonConstants.DEFAULT_USE_JVM_DEFAULT
    )

val Project.keyAlias: String
    get() = findStringProperty(
        CommonConstants.PROPERTY_KEY_SIGNING_KEY_ALIAS,
        CommonConstants.DEFAULT_SIGNING_KEY_ALIAS
    )

val Project.keyPassword: String
    get() = findStringProperty(
        CommonConstants.PROPERTY_KEY_SIGNING_KEY_PASSWORD,
        CommonConstants.DEFAULT_SIGNING_KEY_PASSWORD
    )
val Project.storeFile: String
    get() = findStringProperty(
        CommonConstants.PROPERTY_KEY_SIGNING_STORE_FILE,
        CommonConstants.DEFAULT_SIGNING_STORE_FILE
    )
val Project.storePassword: String
    get() = findStringProperty(
        CommonConstants.PROPERTY_KEY_SIGNING_STORE_PASSWORD,
        CommonConstants.DEFAULT_SIGNING_STORE_PASSWORD
    )