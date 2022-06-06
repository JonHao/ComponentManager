ext {
    set("cm.publish.username", "admin")
    set("cm.publish.password", "admin123")
    set("cm.publish.snapshotsUrl", "http://192.168.28.127:8081/repository/android-snapshots/")
    set("cm.publish.releasesUrl", "http://192.168.28.127:8081/repository/android-releases/")
    set("cm.publish.isRelease", true)
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.28.127:8081/repository/maven-public/")
        }
    }

    dependencies {
        if (ComponentManager.usePublish) {
            classpath(ComponentManager.PublicLibraries.agp)
            classpath(ComponentManager.PublicLibraries.kotlin)
            classpath("top.jonhao.cm:publish:latest.release")
        }
    }
}

subprojects {
    group = findProperty("cm.group").toString()
    version = findProperty("cm.version").toString()
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.28.127:8081/repository/maven-public/")
        }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }

    get("clean").group = "cm"
}