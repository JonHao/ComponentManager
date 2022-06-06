package top.jonhao.cm.publish.entity

import org.gradle.api.artifacts.ExcludeRule

data class DependencyEntity(
    val group: String,
    val name: String,
    var version: String,
    var excludeRules: MutableSet<ExcludeRule>? = null
)

