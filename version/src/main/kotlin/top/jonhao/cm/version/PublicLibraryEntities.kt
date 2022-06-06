package top.jonhao.cm.version

import PublicVersions
import top.jonhao.cm.version.contract.PublicLibraryContract
import top.jonhao.cm.version.entity.DependencyEntity

object PublicLibraryEntities {

    object Common : PublicLibraryContract.Common<DependencyEntity> {
        override val okHttp =
            DependencyEntity.get("com.squareup.okhttp3", "okhttp", PublicVersions.Common.okHttp)

        override val glide =
            DependencyEntity.get("com.github.bumptech.glide", "glide", PublicVersions.Common.glide)

        override val glideCompiler =
            DependencyEntity.get(glide.group, "compiler", PublicVersions.Common.glideCompiler)

        override val gson =
            DependencyEntity.get("com.google.code.gson", "gson", PublicVersions.Common.gson)

        override val material =
            DependencyEntity.get(
                "com.google.android.material",
                "material",
                PublicVersions.Common.material
            )

        override val zxing =
            DependencyEntity.get("com.google.zxing", "core", PublicVersions.Common.zxing)

        override val zxingAndroidEmbedded =
            DependencyEntity.get(
                "com.journeyapps",
                "zxing-android-embedded",
                PublicVersions.Common.zxingAndroidEmbedded
            )

        override val leakCanary =
            DependencyEntity.get(
                "com.squareup.leakcanary",
                "leakcanary-android",
                PublicVersions.Common.leakCanary
            )

    }

    object Kotlin : PublicLibraryContract.Kotlin<DependencyEntity> {
        override val stdlib =
            DependencyEntity.get(
                "org.jetbrains.kotlin",
                "kotlin-stdlib-jdk8",
                PublicVersions.Kotlin.stdlib
            )

        override val reflect =
            DependencyEntity.get(stdlib.group, "kotlin-reflect", PublicVersions.Kotlin.reflect)

        override val coroutines =
            DependencyEntity.get(
                "org.jetbrains.kotlinx",
                "kotlinx-coroutines-android",
                PublicVersions.Kotlin.coroutines
            )
    }

    object AndroidX : PublicLibraryContract.AndroidX<DependencyEntity> {
        // core
        override val core =
            DependencyEntity.get("androidx.core", "core", PublicVersions.AndroidX.core)

        override val coreKtx =
            DependencyEntity.get(core.group, "core-ktx", PublicVersions.AndroidX.coreKtx)

        override val multiDex =
            DependencyEntity.get("androidx.multidex", "multidex", PublicVersions.AndroidX.multiDex)

        override val annotation =
            DependencyEntity.get(
                "androidx.annotation",
                "annotation",
                PublicVersions.AndroidX.annotation
            )

        override val collection =
            DependencyEntity.get(
                "androidx.collection",
                "collection",
                PublicVersions.AndroidX.collection
            )

        override val activity =
            DependencyEntity.get("androidx.activity", "activity", PublicVersions.AndroidX.activity)

        override val activityKtx =
            DependencyEntity.get(
                activity.group,
                "activity-ktx",
                PublicVersions.AndroidX.activityKtx
            )

        override val fragment =
            DependencyEntity.get("androidx.fragment", "fragment", PublicVersions.AndroidX.fragment)

        override val fragmentKtx =
            DependencyEntity.get(
                fragment.group,
                "fragment-ktx",
                PublicVersions.AndroidX.fragmentKtx
            )

        override val appCompat =
            DependencyEntity.get(
                "androidx.appcompat",
                "appcompat",
                PublicVersions.AndroidX.appCompat
            )

        override val constraintLayout =
            DependencyEntity.get(
                "androidx.constraintlayout",
                "constraintlayout",
                PublicVersions.AndroidX.constraintLayout
            )

        override val recyclerView =
            DependencyEntity.get(
                "androidx.recyclerview",
                "recyclerview",
                PublicVersions.AndroidX.recyclerView
            )

        override val vectorDrawable =
            DependencyEntity.get(
                "androidx.vectordrawable",
                "vectordrawable",
                PublicVersions.AndroidX.vectorDrawable
            )

        override val vectorDrawableAnimated =
            DependencyEntity.get(
                vectorDrawable.group,
                "vectordrawable-animated",
                PublicVersions.AndroidX.vectorDrawableAnimated
            )

        // arch
        override val archCoreRuntime =
            DependencyEntity.get(
                "androidx.arch.core",
                "core-runtime",
                PublicVersions.AndroidX.archCoreRuntime
            )

        override val archCoreCommon =
            DependencyEntity.get(
                archCoreRuntime.group,
                "core-common",
                PublicVersions.AndroidX.archCoreCommon
            )

        // lifecycle
        override val lifecycleRuntime =
            DependencyEntity.get(
                "androidx.lifecycle",
                "lifecycle-runtime",
                PublicVersions.AndroidX.lifecycleRuntime
            )

        override val lifecycleCommon =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-common",
                PublicVersions.AndroidX.lifecycleCommon
            )

        override val lifecycleCommonJava8 =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-common-java8",
                PublicVersions.AndroidX.lifecycleCommonJava8
            )

        override val lifecycleLiveData =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-livedata",
                PublicVersions.AndroidX.lifecycleLiveData
            )

        override val lifecycleLiveDataCore =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-livedata-core",
                PublicVersions.AndroidX.lifecycleLiveDataCore
            )

        override val lifecycleLiveDataKtx =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-livedata-ktx",
                PublicVersions.AndroidX.lifecycleLiveDataKtx
            )

        override val lifecycleExtensions =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-extensions",
                PublicVersions.AndroidX.lifecycleExtensions
            )

        override val lifecycleViewModel =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-viewmodel",
                PublicVersions.AndroidX.lifecycleViewModel
            )

        override val lifecycleViewModelKtx =
            DependencyEntity.get(
                lifecycleRuntime.group,
                "lifecycle-viewmodel-ktx",
                PublicVersions.AndroidX.lifecycleViewModelKtx
            )

        // paging
        override val pagingCommon =
            DependencyEntity.get(
                "androidx.paging",
                "paging-common",
                PublicVersions.AndroidX.pagingCommon
            )

        override val pagingCommonKtx =
            DependencyEntity.get(
                pagingCommon.group,
                "paging-common-ktx",
                PublicVersions.AndroidX.pagingCommonKtx
            )

        override val pagingRuntime =
            DependencyEntity.get(
                pagingCommon.group,
                "paging-runtime",
                PublicVersions.AndroidX.pagingRuntime
            )

        override val pagingRuntimeKtx =
            DependencyEntity.get(
                pagingCommon.group,
                "paging-runtime-ktx",
                PublicVersions.AndroidX.pagingRuntimeKtx
            )

        // room
        override val room =
            DependencyEntity.get("androidx.room", "room-runtime", PublicVersions.AndroidX.room)

        override val roomCompiler =
            DependencyEntity.get(room.group, "room-compiler", PublicVersions.AndroidX.roomCompiler)

        override val roomKtx =
            DependencyEntity.get(room.group, "room-ktx", PublicVersions.AndroidX.roomKtx)

        override val roomTest =
            DependencyEntity.get(room.group, "room-testing", PublicVersions.AndroidX.roomTest)
    }

    object UnitTest : PublicLibraryContract.UnitTest<DependencyEntity> {
        override val androidJunitRunner =
            DependencyEntity.get(
                "",
                "androidx.test.runner.AndroidJUnitRunner",
                PublicVersions.UnitTest.androidJunitRunner
            )

        override val junit =
            DependencyEntity.get("junit", "junit", PublicVersions.UnitTest.junit)

        override val androidJunit =
            DependencyEntity.get("androidx.test.ext", "junit", PublicVersions.UnitTest.androidJunit)

        override val espresso =
            DependencyEntity.get(
                "androidx.test.espresso",
                "espresso-core",
                PublicVersions.UnitTest.espresso
            )
    }

    object Plugin : PublicLibraryContract.Plugin<DependencyEntity> {
        override val agp =
            DependencyEntity.get("com.android.tools.build", "gradle", PublicVersions.Plugin.agp)

        override val kotlin =
            DependencyEntity.get(
                "org.jetbrains.kotlin",
                "kotlin-gradle-plugin",
                PublicVersions.Plugin.kotlin
            )

        override val dokka =
            DependencyEntity.get(
                "org.jetbrains.dokka",
                "dokka-gradle-plugin",
                PublicVersions.Plugin.dokka
            )
        override val bytXCommon =
            DependencyEntity.get(
                "com.bytedance.android.byteX",
                "common",
                PublicVersions.Plugin.bytXCommon
            )
    }
}

