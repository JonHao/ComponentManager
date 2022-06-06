package top.jonhao.cm.publish

import top.jonhao.cm.publish.constant.PublishConstants
import top.jonhao.cm.publish.entity.PublicationConfig
import org.gradle.api.Project

object Publish {
    private val publicationConfigMap = HashMap<String, PublicationConfig>()

    var useLock: Boolean = PublishConstants.DEFAULT_USE_LOCK

    fun setPublicationConfig(project: Project, publicationConfig: PublicationConfig) {
        publicationConfigMap[project.name] = publicationConfig
    }

    fun getPublicationConfig(project: Project): PublicationConfig? {
        return publicationConfigMap[project.name]
    }
}