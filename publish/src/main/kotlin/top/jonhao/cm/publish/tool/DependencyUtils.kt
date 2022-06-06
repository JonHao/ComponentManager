package top.jonhao.cm.publish.tool

import top.jonhao.cm.publish.entity.DependencyEntity
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.result.ResolvedDependencyResult
import org.gradle.api.internal.artifacts.configurations.ResolvableDependenciesInternal
import org.gradle.api.internal.artifacts.dsl.dependencies.DependencyLockingProvider
import org.gradle.internal.locking.DefaultDependencyLockingHandler

internal object DependencyUtils {

    fun getDependencies(project: Project, useLock: Boolean): Set<DependencyEntity> {
        val result = mutableSetOf<DependencyEntity>()
        val dependencyEntities: Set<DependencyEntity> = if (useLock) {
            val temp = getLockedDependencies(project)
            temp.ifEmpty {
                getSelectedDependencies(project)
            }
        } else {
            getSelectedDependencies(project)
        }

        project.configurations.getByName("api").allDependencies.onEach { dependency ->
            if (dependency !is ModuleDependency) {
                return@onEach
            }
            val moduleDependency: ModuleDependency = dependency
            moduleDependency.apply {
                var isSubproject = true
                dependencyEntities.onEach label@{ dependencyEntity ->
                    if (result.contains(dependencyEntity)) {
                        return@label
                    }
                    if (dependencyEntity.group == group && dependencyEntity.name == name) {
                        result.add(dependencyEntity.apply {
                            this.excludeRules = moduleDependency.excludeRules
                        })
                        isSubproject = false
                    }
                }
                if (isSubproject) {
                    if (group != null && version != null) {
                        result.add(
                            DependencyEntity(group!!, name, version!!).apply {
                                this.excludeRules = moduleDependency.excludeRules
                            }
                        )
                    }
                }
            }
        }
        return result
    }

    /**
     * 获取锁文件中的依赖
     * 需要在 afterEvaluate 之后调用
     *
     * @param project Project project
     * @return List<DependencyEntity> 依赖列表
     */
    private fun getLockedDependencies(project: Project): Set<DependencyEntity> {
        val result = mutableSetOf<DependencyEntity>()
        if (project.dependencyLocking !is DefaultDependencyLockingHandler) {
            return result
        }
        val field =
            DefaultDependencyLockingHandler::class.java.getDeclaredField("dependencyLockingProvider")
        field.isAccessible = true
        val dependencyLockingProvider: DependencyLockingProvider =
            field.get(project.dependencyLocking) as DependencyLockingProvider

        dependencyLockingProvider.loadLockState("debugRuntimeClasspath").lockedDependencies.onEach {
            result.add(DependencyEntity(it.group, it.module, it.version))
        }
        return result
    }

    /**
     * 获取依赖
     * 需要在 afterEvaluate 之后调用
     *
     * @param project Project project
     * @return List<DependencyEntity> 依赖列表
     */
    private fun getSelectedDependencies(project: Project): Set<DependencyEntity> {
        val result = mutableSetOf<DependencyEntity>()
        var configuration: Configuration? = null
        try {
            configuration = project.configurations.getByName("apiDependenciesMetadata")
        } catch (e: Exception) {
            // ignore
        }
        if (configuration == null) {
            try {
                configuration = project.configurations.getByName("runtimeClasspath")
            } catch (e: Exception) {
                // ignore
            }
        }

        configuration?.apply {
            if (incoming !is ResolvableDependenciesInternal) {
                return@apply
            }
            val incoming = this.incoming as ResolvableDependenciesInternal
            val resolutionResult = incoming.getResolutionResult(object : Action<Throwable> {
                override fun execute(t: Throwable) {
                    println(t.message)
                }
            })

            resolutionResult.allDependencies {
                if (this is ResolvedDependencyResult) {
                    val array = selected.id.displayName.split(":")
                    if (array.size == 3) {
                        result.add(DependencyEntity(array[0], array[1], array[2]))
                    }
                }
            }
        }
        return result
    }
}