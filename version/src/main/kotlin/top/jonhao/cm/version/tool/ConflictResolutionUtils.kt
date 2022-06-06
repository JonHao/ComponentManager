package top.jonhao.cm.version.tool

import top.jonhao.cm.version.entity.DependencyEntity
import top.jonhao.cm.version.PublicLibraryEntities
import org.gradle.api.artifacts.ResolutionStrategy

internal object ConflictResolutionUtils {

    private val jetbrainsAnnotation = DependencyEntity.get(
        "org.jetbrains", "annotations", "16.0.1"
    )

    fun unifiedDependenciesVersion(resolutionStrategy: ResolutionStrategy) {
        resolutionStrategy.eachDependency {
            when (requested.group) {
                PublicLibraryEntities.Kotlin.coroutines.group -> {
                    if (requested.name.contains(PublicLibraryEntities.Kotlin.coroutines.name)) {
                        useVersion(PublicLibraryEntities.Kotlin.coroutines.version)
                    }
                }
                PublicLibraryEntities.AndroidX.collection.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.collection.name -> useVersion(
                            PublicLibraryEntities.AndroidX.collection.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.fragment.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.fragment.name -> useVersion(
                            PublicLibraryEntities.AndroidX.fragment.version
                        )
                        PublicLibraryEntities.AndroidX.fragmentKtx.name -> useVersion(
                            PublicLibraryEntities.AndroidX.fragmentKtx.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.appCompat.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.appCompat.name -> useVersion(
                            PublicLibraryEntities.AndroidX.appCompat.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.constraintLayout.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.constraintLayout.name -> useVersion(
                            PublicLibraryEntities.AndroidX.constraintLayout.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.annotation.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.annotation.name -> useVersion(
                            PublicLibraryEntities.AndroidX.annotation.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.core.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.core.name -> useVersion(
                            PublicLibraryEntities.AndroidX.core.version
                        )
                        PublicLibraryEntities.AndroidX.coreKtx.name -> useVersion(
                            PublicLibraryEntities.AndroidX.coreKtx.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.archCoreRuntime.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.archCoreRuntime.name -> useVersion(
                            PublicLibraryEntities.AndroidX.archCoreRuntime.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.lifecycleRuntime.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.lifecycleCommon.name -> useVersion(
                            PublicLibraryEntities.AndroidX.lifecycleCommon.version
                        )
                        PublicLibraryEntities.AndroidX.lifecycleLiveData.name -> useVersion(
                            PublicLibraryEntities.AndroidX.lifecycleLiveData.version
                        )
                        PublicLibraryEntities.AndroidX.lifecycleLiveDataCore.name -> useVersion(
                            PublicLibraryEntities.AndroidX.lifecycleLiveDataCore.version
                        )
                        PublicLibraryEntities.AndroidX.lifecycleRuntime.name -> useVersion(
                            PublicLibraryEntities.AndroidX.lifecycleRuntime.version
                        )
                        PublicLibraryEntities.AndroidX.lifecycleViewModel.name -> useVersion(
                            PublicLibraryEntities.AndroidX.lifecycleViewModel.version
                        )
                        PublicLibraryEntities.AndroidX.lifecycleViewModelKtx.name -> useVersion(
                            PublicLibraryEntities.AndroidX.lifecycleViewModelKtx.version
                        )
                    }
                }
                PublicLibraryEntities.AndroidX.vectorDrawable.group -> {
                    when (requested.name) {
                        PublicLibraryEntities.AndroidX.vectorDrawable.name -> useVersion(
                            PublicLibraryEntities.AndroidX.vectorDrawable.version
                        )
                        PublicLibraryEntities.AndroidX.vectorDrawableAnimated.name -> useVersion(
                            PublicLibraryEntities.AndroidX.vectorDrawableAnimated.version
                        )
                    }
                }
                jetbrainsAnnotation.group -> {
                    when (requested.name) {
                        jetbrainsAnnotation.name -> useVersion(
                            jetbrainsAnnotation.version
                        )
                    }
                }
            }
        }
    }
}