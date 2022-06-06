package top.jonhao.cm.version

import top.jonhao.cm.version.tool.ConflictResolutionUtils
import org.gradle.api.Project

object Version {
    /** [rootProject] late init when the root project gradle script is built. */
    lateinit var rootProject: Project

    var isDebugMode: Boolean = false

    var isUnifiedDependenciesVersion = true

    fun init(rootProject: Project) {
        this.rootProject = rootProject

        if(isUnifiedDependenciesVersion){
            rootProject.subprojects {
                configurations.all {
                    ConflictResolutionUtils.unifiedDependenciesVersion(resolutionStrategy)
                }
            }
        }
    }
}