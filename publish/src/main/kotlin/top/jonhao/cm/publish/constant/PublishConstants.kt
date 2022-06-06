package top.jonhao.cm.publish.constant

object PublishConstants {

    // Base info
    const val POM_NAME = "cm.publish.pom.name"
    const val POM_DESCRIPTION = "cm.publish.pom.description"
    const val POM_INCEPTION_YEAR = "cm.publish.pom.inceptionYear"
    const val POM_URL = "cm.publish.pom.url"

    // License info
    const val POM_LICENCE_NAME = "cm.publish.pom.license.name"
    const val POM_LICENCE_URL = "cm.publish.pom.license.url"
    const val POM_LICENCE_DISTRIBUTION = "cm.publish.pom.license.distribution"
    const val POM_LICENCE_COMMENTS = "cm.publish.pom.license.comments"

    // SCM
    const val POM_SCM_URL = "cm.publish.pom.scm.publish.url"
    const val POM_SCM_CONNECTION = "cm.publish.pom.scm.publish.connection"
    const val POM_SCM_DEVELOPER_CONNECTION = "cm.publish.pom.scm.publish.developerConnection"

    // Developer info
    const val POM_DEVELOPER_ID = "cm.publish.pom.developer.id"
    const val POM_DEVELOPER_NAME = "cm.publish.pom.developer.name"
    const val POM_DEVELOPER_EMAIL = "cm.publish.pom.developer.email"
    const val POM_DEVELOPER_URL = "cm.publish.pom.developer.url"

    // Organization info
    const val POM_ORGANIZATION_NAME = "cm.publish.pom.organization.name"
    const val POM_ORGANIZATION_URL = "cm.publish.pom.organization.url"

    // Repository info
    const val PUBLISH_KEY_USERNAME = "cm.publish.username"
    const val PUBLISH_KEY_PASSWORD = "cm.publish.password"
    const val PUBLISH_KEY_URL_SNAPSHOTS = "cm.publish.snapshotsUrl"
    const val PUBLISH_KEY_URL_RELEASES = "cm.publish.releasesUrl"
    const val PUBLISH_KEY_IS_PUBLISH_RELEASES = "cm.publish.isPublishReleases"

    const val DEFAULT_USERNAME = ""
    const val DEFAULT_PASSWORD = ""
    const val DEFAULT_URL_SNAPSHOTS = ""
    const val DEFAULT_URL_RELEASES = ""
    const val DEFAULT_IS_PUBLISH_RELEASES = false
    const val DEFAULT_WITH_SOURCE = true
    const val DEFAULT_WITH_DOCUMENT = false
    const val DEFAULT_USE_LOCK = true

    const val PROPERTY_KEY_GROUP = "cm.group"
    const val PROPERTY_KEY_NAME = "cm.name"
    const val PROPERTY_KEY_VERSION = "cm.version"
    const val PROPERTY_KEY_VERSION_CODE = "cm.versionCode"
    const val PROPERTY_KEY_WITH_SOURCE = "cm.withSource"
    const val PROPERTY_KEY_WITH_DOCUMENT = "cm.withDocument"
    const val PROPERTY_KEY_USE_LOCK = "cm.useLock"

    const val SUFFIX_SNAPSHOT = "-SNAPSHOT"
    const val SUFFIX_RELEASE = ""

    // Other
    const val PUBLICATION_PLUGIN_ID = "maven-publish"

    const val DEFAULT_PUBLICATION_NAME = "cm"
}