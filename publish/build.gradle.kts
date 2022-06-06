plugins {
    `kotlin-dsl`
    if (ComponentManager.usePublish) {
        id(ComponentManager.PrivatePlugins.publish)
    } else {
        `maven-publish`
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}

dependencies {
    compileOnly(ComponentManager.PublicLibraries.agp)
}