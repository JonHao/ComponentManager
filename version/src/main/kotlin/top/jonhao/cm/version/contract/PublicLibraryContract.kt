package top.jonhao.cm.version.contract

interface PublicLibraryContract {
    interface Kotlin<T> {
        val stdlib: T
        val reflect: T
        val coroutines: T
    }

    interface Common<T> {
        val okHttp: T
        val glide: T
        val glideCompiler: T
        val gson: T
        val material: T
        val zxing: T
        val zxingAndroidEmbedded: T
        val leakCanary: T
    }

    interface AndroidX<T> {
        // core
        val core: T
        val coreKtx: T
        val multiDex: T
        val annotation: T
        val collection: T
        val activity: T
        val activityKtx: T
        val fragment: T
        val fragmentKtx: T

        val appCompat: T
        val constraintLayout: T
        val recyclerView: T
        val vectorDrawable: T
        val vectorDrawableAnimated: T

        // arch
        val archCoreRuntime: T
        val archCoreCommon: T
        val lifecycleRuntime: T
        val lifecycleCommon: T
        val lifecycleCommonJava8: T
        val lifecycleLiveData: T
        val lifecycleLiveDataCore: T
        val lifecycleLiveDataKtx: T
        val lifecycleExtensions: T
        val lifecycleViewModel: T
        val lifecycleViewModelKtx: T

        // paging
        val pagingCommon: T
        val pagingCommonKtx: T
        val pagingRuntime: T
        val pagingRuntimeKtx: T

        // room
        val room: T
        val roomCompiler: T
        val roomKtx: T
        val roomTest: T
    }

    interface UnitTest<T> {
        val androidJunitRunner: T
        val junit: T
        val androidJunit: T
        val espresso: T
    }

    interface Plugin<T> {
        val agp: T
        val kotlin: T
        val dokka: T
        val bytXCommon: T
    }
}