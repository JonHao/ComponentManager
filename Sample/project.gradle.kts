allprojects {
    if (project.name == project.rootProject.name) {
        project.rootProject.extra.apply {
            set("cm.gradleVersion", "7.4.2")
            set("cm.compileSdk", 31)
            set("cm.minSdk", 18)
            set("cm.targetSdk", 31)

            set("cm.publish.username", "developer")
            set("cm.publish.password", "123456")
            set(
                "cm.publish.snapshotsUrl",
                "http://192.168.28.127:8081/repository/android-snapshots/"
            )
            set("cm.publish.releasesUrl", "http://192.168.28.127:8081/repository/android-releases/")
            set("cm.publish.isPublishReleases", false)
        }
        buildscript {
            repositories {
                google()
                mavenCentral()
                maven {
                    isAllowInsecureProtocol = true
                    url = uri("http://192.168.28.127:8081/repository/maven-public/")
                }
            }
            dependencies {
                classpath("com.android.tools.build:gradle:7.2.1")
                classpath("top.jonhao.cm:common:latest.release")
                classpath("top.jonhao.cm:version:latest.release")
            }

            configurations.all {
                // cache dynamic versions for 0 seconds
                resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
                // don't cache changing modules at all
                resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
            }

            dependencyLocking {
                val useLock =
                    project.findProperty("cm.useLock")?.toString()?.toBoolean() ?: true
                if (useLock) {
                    lockAllConfigurations()
                }
            }
        }
    }

    repositories {
        google()
        mavenCentral()
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.28.127:8081/repository/maven-public/")
        }
    }

    configurations.all {
        // cache dynamic versions for 0 seconds
        resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
        // don't cache changing modules at all
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }

    dependencyLocking {
        val useLock =
            project.findProperty("cm.useLock")?.toString()?.toBoolean() ?: true
        if (useLock) {
            lockAllConfigurations()
        }
    }
}

