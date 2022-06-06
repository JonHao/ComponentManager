package top.jonhao.cm.common.tool

import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

object KotlinOptionsUtils {

    fun configKotlinOptions(extensionAware: ExtensionAware, name: String, useJvmDefault: Boolean) {
        extensionAware.extensions.getByName(name).apply {
            if (this is KotlinJvmOptions) {
                configKotlinOptions(this, useJvmDefault)
            }
        }
    }

    fun configKotlinOptions(kotlinOptions: KotlinJvmOptions, useJvmDefault: Boolean) {
        kotlinOptions.apply {
            jvmTarget = "1.8"
            if (useJvmDefault) {
                useJvmDefault(this)
            }
        }
    }

    private fun useJvmDefault(kotlinOptions: KotlinJvmOptions) {
        kotlinOptions.apply {
            if (freeCompilerArgs.isEmpty() && freeCompilerArgs !is MutableList<String>) {
                freeCompilerArgs = ArrayList()
            }

            if (freeCompilerArgs !is MutableList<String>) {
                return
            }

            (freeCompilerArgs as MutableList<String>).apply {
                add("-Xjvm-default")
                add("all-compatibility")
            }
        }
    }
}