plugins {
    `kotlin-dsl`
    if(ComponentManager.usePublish){
        id(ComponentManager.PrivatePlugins.publish)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}