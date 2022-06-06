import top.jonhao.cm.base.extension.version

plugins {
    id(PrivatePlugins.androidApplication)
}

dependencies {
    implementation(PublicLibraries.AndroidX.core)
    implementation(PublicLibraries.AndroidX.appCompat version "1.2.0")
    implementation(PublicLibraries.Common.material)
    implementation(PublicLibraries.AndroidX.constraintLayout)
}