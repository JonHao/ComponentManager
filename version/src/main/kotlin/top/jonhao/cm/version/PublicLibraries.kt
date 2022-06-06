import top.jonhao.cm.version.contract.PublicLibraryContract
import top.jonhao.cm.version.PublicLibraryEntities

object PublicLibraries {
    object Common : PublicLibraryContract.Common<String> {
        override val okHttp = PublicLibraryEntities.Common.okHttp.toString()
        override val glide =
            PublicLibraryEntities.Common.glide.toString()
        override val glideCompiler =
            PublicLibraryEntities.Common.glideCompiler.toString()
        override val gson =
            PublicLibraryEntities.Common.gson.toString()
        override val material =
            PublicLibraryEntities.Common.material.toString()
        override val zxing =
            PublicLibraryEntities.Common.zxing.toString()
        override val zxingAndroidEmbedded =
            PublicLibraryEntities.Common.zxingAndroidEmbedded.toString()
        override val leakCanary =
            PublicLibraryEntities.Common.leakCanary.toString()
    }

    object Kotlin : PublicLibraryContract.Kotlin<String> {
        override val stdlib =
            PublicLibraryEntities.Kotlin.stdlib.toString()
        override val reflect =
            PublicLibraryEntities.Kotlin.reflect.toString()
        override val coroutines =
            PublicLibraryEntities.Kotlin.coroutines.toString()
    }

    object AndroidX : PublicLibraryContract.AndroidX<String> {
        // core
        override val core =
            PublicLibraryEntities.AndroidX.core.toString()
        override val coreKtx =
            PublicLibraryEntities.AndroidX.coreKtx.toString()
        override val multiDex =
            PublicLibraryEntities.AndroidX.multiDex.toString()
        override val annotation =
            PublicLibraryEntities.AndroidX.annotation.toString()
        override val collection =
            PublicLibraryEntities.AndroidX.collection.toString()
        override val activity =
            PublicLibraryEntities.AndroidX.activity.toString()
        override val activityKtx =
            PublicLibraryEntities.AndroidX.activityKtx.toString()
        override val fragment =
            PublicLibraryEntities.AndroidX.fragment.toString()
        override val fragmentKtx =
            PublicLibraryEntities.AndroidX.fragmentKtx.toString()

        override val appCompat =
            PublicLibraryEntities.AndroidX.appCompat.toString()
        override val constraintLayout =
            PublicLibraryEntities.AndroidX.constraintLayout.toString()
        override val recyclerView =
            PublicLibraryEntities.AndroidX.recyclerView.toString()
        override val vectorDrawable =
            PublicLibraryEntities.AndroidX.vectorDrawable.toString()
        override val vectorDrawableAnimated =
            PublicLibraryEntities.AndroidX.vectorDrawableAnimated.toString()

        // arch
        override val archCoreRuntime =
            PublicLibraryEntities.AndroidX.archCoreRuntime.toString()
        override val archCoreCommon =
            PublicLibraryEntities.AndroidX.archCoreCommon.toString()

        // lifecycle
        override val lifecycleRuntime =
            PublicLibraryEntities.AndroidX.lifecycleRuntime.toString()
        override val lifecycleCommon =
            PublicLibraryEntities.AndroidX.lifecycleCommon.toString()
        override val lifecycleCommonJava8 =
            PublicLibraryEntities.AndroidX.lifecycleCommonJava8.toString()
        override val lifecycleLiveData =
            PublicLibraryEntities.AndroidX.lifecycleLiveData.toString()
        override val lifecycleLiveDataCore =
            PublicLibraryEntities.AndroidX.lifecycleLiveDataCore.toString()
        override val lifecycleLiveDataKtx =
            PublicLibraryEntities.AndroidX.lifecycleLiveDataKtx.toString()
        override val lifecycleExtensions =
            PublicLibraryEntities.AndroidX.lifecycleExtensions.toString()
        override val lifecycleViewModel =
            PublicLibraryEntities.AndroidX.lifecycleViewModel.toString()
        override val lifecycleViewModelKtx =
            PublicLibraryEntities.AndroidX.lifecycleViewModelKtx.toString()

        // paging
        override val pagingCommon =
            PublicLibraryEntities.AndroidX.pagingCommon.toString()
        override val pagingCommonKtx =
            PublicLibraryEntities.AndroidX.pagingCommonKtx.toString()
        override val pagingRuntime =
            PublicLibraryEntities.AndroidX.pagingRuntime.toString()
        override val pagingRuntimeKtx =
            PublicLibraryEntities.AndroidX.pagingRuntimeKtx.toString()

        // room
        override val room =
            PublicLibraryEntities.AndroidX.room.toString()
        override val roomCompiler =
            PublicLibraryEntities.AndroidX.roomCompiler.toString()
        override val roomKtx =
            PublicLibraryEntities.AndroidX.roomKtx.toString()
        override val roomTest =
            PublicLibraryEntities.AndroidX.roomTest.toString()
    }

    object UnitTest : PublicLibraryContract.UnitTest<String> {
        override val androidJunitRunner =
            PublicLibraryEntities.UnitTest.androidJunitRunner.toString()
        override val junit =
            PublicLibraryEntities.UnitTest.junit.toString()
        override val androidJunit =
            PublicLibraryEntities.UnitTest.androidJunit.toString()
        override val espresso =
            PublicLibraryEntities.UnitTest.espresso.toString()
    }

    object Plugin : PublicLibraryContract.Plugin<String> {
        override val agp =
            PublicLibraryEntities.Plugin.agp.toString()
        override val kotlin =
            PublicLibraryEntities.Plugin.kotlin.toString()
        override val dokka =
            PublicLibraryEntities.Plugin.dokka.toString()
        override val bytXCommon =
            PublicLibraryEntities.Plugin.bytXCommon.toString()
    }
}