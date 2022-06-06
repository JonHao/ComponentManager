import top.jonhao.cm.version.contract.PublicLibraryContract

object PublicVersions {

    object Android {
        const val compileSdk = 32
        const val minSdk = 18
        const val targetSdk = 32

        const val jvmTarget = "1.8"
    }

    object Common : PublicLibraryContract.Common<String> {
        override val okHttp = "4.9.3"
        override val glide = "4.13.2"
        override val glideCompiler = glide
        override val gson = "2.9.0"
        override val material = "1.6.0"
        override val zxing = "3.5.0"
        override val zxingAndroidEmbedded = "4.3.0"
        override val leakCanary = "2.9.1"
    }

    object Kotlin : PublicLibraryContract.Kotlin<String> {
        override val stdlib = "1.6.20"
        override val reflect = stdlib
        override val coroutines = "1.5.2"
    }

    object AndroidX : PublicLibraryContract.AndroidX<String> {
        // core
        override val core = "1.7.0"
        override val coreKtx = core
        override val multiDex = "2.0.1"
        override val annotation = "1.3.0"
        override val collection = "1.2.0"
        override val activity = "1.4.0"
        override val activityKtx = activity
        override val fragment = "1.4.1"
        override val fragmentKtx = fragment

        override val appCompat = "1.3.1"
        override val constraintLayout = "2.1.2"
        override val recyclerView = "1.2.0"
        override val vectorDrawable = "1.1.0"
        override val vectorDrawableAnimated = vectorDrawable

        // arch
        override val archCoreRuntime = "2.1.0"
        override val archCoreCommon = archCoreRuntime

        // lifecycle
        override val lifecycleRuntime = "2.4.1"
        override val lifecycleCommon = lifecycleRuntime
        override val lifecycleCommonJava8 = lifecycleRuntime
        override val lifecycleLiveData = lifecycleRuntime
        override val lifecycleLiveDataCore = lifecycleRuntime
        override val lifecycleLiveDataKtx = lifecycleRuntime
        override val lifecycleExtensions = "2.2.0"
        override val lifecycleViewModel = lifecycleRuntime
        override val lifecycleViewModelKtx = lifecycleRuntime

        // paging
        override val pagingCommon = "3.1.1"
        override val pagingCommonKtx = pagingCommon
        override val pagingRuntime = pagingCommon
        override val pagingRuntimeKtx = pagingCommon

        // room
        override val room = "2.4.2"
        override val roomCompiler = room
        override val roomKtx = room
        override val roomTest = room
    }

    object UnitTest : PublicLibraryContract.UnitTest<String> {
        override val androidJunitRunner = ""
        override val junit = "4.13.2"
        override val androidJunit = "1.1.3"
        override val espresso = "3.4.0"
    }

    object Plugin : PublicLibraryContract.Plugin<String> {
        override val agp = "7.2.0"
        override val kotlin = Kotlin.stdlib
        override val dokka = Kotlin.stdlib
        override val bytXCommon = "0.3.0"
    }
}