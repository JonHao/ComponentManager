package top.jonhao.cm.common.constant

object CommonConstants {

    const val PROPERTY_KEY_USE_KOTLIN = "cm.useKotlin"
    const val PROPERTY_KEY_USE_JVM_DEFAULT = "cm.useJvmDefault"
    const val PROPERTY_GRADLE_VERSION = "cm.gradleVersion"

    const val PROPERTY_KEY_APPLICATION_ID = "cm.applicationId"
    const val PROPERTY_KEY_COMPILE_SDK = "cm.compileSdk"
    const val PROPERTY_KEY_MIN_SDK = "cm.minSdk"
    const val PROPERTY_KEY_TARGET_SDK = "cm.targetSdk"

    const val PROPERTY_KEY_SIGNING_KEY_ALIAS = "cm.keyAlias"
    const val PROPERTY_KEY_SIGNING_KEY_PASSWORD = "cm.keyPassword"
    const val PROPERTY_KEY_SIGNING_STORE_FILE = "cm.storeFile"
    const val PROPERTY_KEY_SIGNING_STORE_PASSWORD = "cm.storePassword"

    const val DEFAULT_USE_KOTLIN = true
    const val DEFAULT_USE_JVM_DEFAULT = false

    const val DEFAULT_SIGNING_KEY_ALIAS = "developer"
    const val DEFAULT_SIGNING_KEY_PASSWORD = "123456"
    const val DEFAULT_SIGNING_STORE_FILE: String = "developer.jks"
    const val DEFAULT_SIGNING_STORE_PASSWORD = "123456"

    const val PROJECT_NAME = "cm"

    /** Recommend gradle version [RECOMMEND_GRADLE_VERSION]. */
    const val RECOMMEND_GRADLE_VERSION = "7.4.2"
}