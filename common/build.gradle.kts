plugins {
    `kotlin-dsl`
    if (ComponentManager.usePublish) {
        id(ComponentManager.PrivatePlugins.publish)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}

dependencies {
    compileOnly(ComponentManager.PublicLibraries.agp)
    api(ComponentManager.PublicLibraries.kotlin)
    api(ComponentManager.PublicLibraries.dokka)
    api(ComponentManager.PublicLibraries.commonIO)

    api(project(":base"))
    api(project(":publish"))
    compileOnly(project(":version"))
}