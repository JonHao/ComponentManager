package top.jonhao.cm.version.entity

import top.jonhao.cm.base.extension.times
import top.jonhao.cm.version.Version
import top.jonhao.cm.version.constant.DependencyType
import top.jonhao.cm.version.constant.VersionConstants

class PrivateLibraryEntity constructor(
    override val group: String,
    override val name: String,
    override var version: String = VersionConstants.LATEST_VERSION,
    var sourceUrl: String = "",
    var sourceProject: String = "",
    var branch: String = VersionConstants.DEFAULT_BRANCH,
    var tag: String? = null,
    var dependencyType: DependencyType = DependencyType.RELEASE
) : DependencyEntity(group, name, version) {


    companion object {
        fun get(
            group: String,
            name: String,
            version: String = VersionConstants.LATEST_VERSION,
            sourceUrl: String = "",
            sourceProject: String = "",
            branch: String = VersionConstants.DEFAULT_BRANCH,
            tag: String = version,
            dependencyType: DependencyType = DependencyType.RELEASE
        ) = PrivateLibraryEntity(
            group,
            name,
            version,
            sourceUrl,
            sourceProject,
            branch,
            tag,
            dependencyType
        )

        fun getNotation(
            group: String,
            name: String,
            version: String = VersionConstants.LATEST_VERSION,
            sourceUrl: String = "",
            sourceProject: String = "",
            branch: String = VersionConstants.DEFAULT_BRANCH,
            tag: String = version,
            dependencyType: DependencyType = DependencyType.RELEASE
        ) = get(
            group,
            name,
            version,
            sourceUrl,
            sourceProject,
            branch,
            tag,
            dependencyType
        ).toDependencyNotation()
    }

    fun toDependencyNotation(): Any {
        var tempDependencyType = dependencyType
        if (Version.isDebugMode) {
            tempDependencyType = DependencyType.SOURCE
        }

        return when (tempDependencyType) {
            DependencyType.SOURCE -> {
                if (sourceProject.isNotEmpty()) {
                    Version.rootProject.project(":${sourceProject}")
                } else {
                    group * name * version * VersionConstants.SUFFIX_SNAPSHOT
                }
            }
            DependencyType.SNAPSHOT -> group * name * version * VersionConstants.SUFFIX_SNAPSHOT
            DependencyType.RELEASE -> group * name * version * VersionConstants.SUFFIX_RELEASE
        }
    }

    override fun toString(): String {
        val notation = toDependencyNotation()
        return if (notation is String) {
            notation
        } else {
            super.toString()
        }
    }

    fun useSource(): Any {
        dependencyType = DependencyType.SOURCE
        return toDependencyNotation()
    }

    fun useSnapshot(): Any {
        dependencyType = DependencyType.SNAPSHOT
        return toDependencyNotation()
    }

    fun useRelease(): Any {
        dependencyType = DependencyType.RELEASE
        return toDependencyNotation()
    }
}