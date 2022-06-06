package top.jonhao.cm.publish.entity

import top.jonhao.cm.publish.constant.PublishConstants
import org.gradle.api.Task

data class PublicationConfig(
    var publicationName: String = PublishConstants.DEFAULT_PUBLICATION_NAME,
    var outputFilePath: String? = null,
    var javadocJar: Task? = null,
    var sourcesJar: Task? = null
)
