package top.jonhao.cm.common.tool

import PublicLibraries
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

object UnitTestUtils {

    fun apply(target: Project) {
        val commonExtension = target.extensions.getByType(BaseExtension::class.java)

        commonExtension.run {
            defaultConfig {
                testInstrumentationRunner = PublicLibraries.UnitTest.androidJunitRunner
            }
        }

        target.dependencies.apply {
            add("testImplementation", PublicLibraries.UnitTest.junit)
            add("androidTestImplementation", PublicLibraries.UnitTest.androidJunit)
            add("androidTestImplementation", PublicLibraries.UnitTest.espresso)
        }
    }
}