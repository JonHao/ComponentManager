package top.jonhao.cm.publish

open class CMPublishingExtension {
    companion object {
        const val NAME = "cmPublishing"
    }

    var username: String? = null
    var password: String? = null
    var releasesUrl: String? = null
    var snapshotsUrl: String? = null
    var isPublishReleases: Boolean? = null
}