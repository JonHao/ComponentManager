import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import top.jonhao.cm.base.extension.findGlobalProperty
import top.jonhao.cm.base.tool.BaseUtils
import top.jonhao.cm.common.constant.CommonConstants
import top.jonhao.cm.common.task.CleanGradleCacheTask
import top.jonhao.cm.common.task.CleanTask
import top.jonhao.cm.common.task.DeleteAllLocksTask
import top.jonhao.cm.common.task.GenerateAllLocksTask
import top.jonhao.cm.publish.Publish
import top.jonhao.cm.version.Version
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

object Common {

    fun init(rootProject: Project) {
        setRootProject(rootProject)
        checkGradleVersion(rootProject)
        registerTasks(rootProject)
    }

    fun setRootProject(rootProject: Project) {
        Version.init(rootProject)
    }

    fun getRootProject() = Version.rootProject

    fun setDebugMode(isDebugMode: Boolean) {
        Version.isDebugMode = isDebugMode
    }

    fun isDebugMode() = Version.isDebugMode

    fun setUseLock(useLock: Boolean) {
        Publish.useLock = useLock
    }

    fun setUnifiedDependenciesVersion(isUnifiedDependenciesVersion: Boolean) {
        Version.isUnifiedDependenciesVersion = isUnifiedDependenciesVersion
    }

    fun isUnifiedDependenciesVersion() = Version.isUnifiedDependenciesVersion

    private fun checkGradleVersion(project: Project) {
        val currentGradleVersion = project.gradle.gradleVersion
        val recommendGradleVersion = project.findGlobalProperty(
            CommonConstants.PROPERTY_GRADLE_VERSION,
            CommonConstants.RECOMMEND_GRADLE_VERSION
        )

        if (currentGradleVersion != recommendGradleVersion) {
            println("Current gradle version is $currentGradleVersion,but the recommend gradle version is $recommendGradleVersion!")
            println("Updating gradle-wrapper.properties,set gradle version:$recommendGradleVersion!")
            val gradleWrapperFile =
                File("${project.projectDir}", "gradle/wrapper/gradle-wrapper.properties")
            val properties = Properties()
            properties.load(FileInputStream(gradleWrapperFile))

            properties.setProperty(
                "distributionUrl",
                "https://services.gradle.org/distributions/gradle-${recommendGradleVersion}-all.zip"
            )
            gradleWrapperFile.setWritable(true)
            properties.store(FileOutputStream(gradleWrapperFile), "")

            project.exec {
                val command = StringBuilder()
                project.gradle.startParameter.taskNames.onEach {
                    command.append(it)
                }

                workingDir = project.projectDir

                BaseUtils.gradleCommandLine(this, project.rootDir.canonicalPath, "--stop")
                BaseUtils.gradleCommandLine(
                    this,
                    project.rootDir.canonicalPath,
                    command.toString()
                )
            }
        }
    }

    private fun registerTasks(project: Project) {
        project.tasks.apply {
            register<CleanTask>("clean") {
                group = CommonConstants.PROJECT_NAME
            }
            register<CleanGradleCacheTask>("cleanGradleCache") {
                group = CommonConstants.PROJECT_NAME
            }
            register<GenerateAllLocksTask>("generateAllLocks") {
                group = CommonConstants.PROJECT_NAME
            }.dependsOn("deleteAllLocks")
            register<DeleteAllLocksTask>("deleteAllLocks") {
                group = CommonConstants.PROJECT_NAME
            }
        }
    }
}