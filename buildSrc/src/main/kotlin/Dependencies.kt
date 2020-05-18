object Plugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.junit5Plugin}"
    const val safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val allOpen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
}

object Libs {

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val axEspressoIdlingResource =
        "androidx.test.espresso.idling:idling-concurrent:${Versions.axEspresso}"

    object Kapt {
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val databindingCompiler =
            "androidx.databinding:databinding-compiler:${Versions.databinding}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerAndroidSupportCompiler =
            "com.google.dagger:dagger-android-processor:${Versions.dagger}"
        const val axLifecycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    }

    object RunTime {
        const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
        const val junitVintageEngine = "org.junit.vintage:junit-vintage-engine:${Versions.junit5}"
    }

    object Kotlin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Room {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

        val all = listOf(
            room,
            roomKtx
        )
    }

    object Ui {
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        val all = listOf(
            recyclerView,
            materialDesign,
            constraintLayout
        )
    }


    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttp3LoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

        val all = listOf(
            retrofit,
            okhttp3,
            okhttp3LoggingInterceptor,
            gson,
            gsonConverter
        )
    }

    object Dagger {
        const val daggerRuntime = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val daggerAndroidSupport =
            "com.google.dagger:dagger-android-support:${Versions.dagger}"

        val all = listOf(
            daggerRuntime,
            daggerAndroid,
            daggerAndroidSupport
        )
    }

    object Navigation {
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"

        val all = listOf(
            navigation,
            navigationUi
        )
    }


    object Lifecycle {
        const val axViewModelExt =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val axLiveDataExt = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val axLifeCycleExt = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val axViewModelSavedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

        val all = listOf(
            axViewModelExt,
            axLiveDataExt,
            axLifeCycleExt,
            axViewModelSavedState
        )
    }

    object Test {
        const val roboletric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val spek = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
        const val spekRunner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"

        const val junit5 = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
        const val junit5Param = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
        const val junit4 = "junit:junit:${Versions.junit4}"
//        const val junit5TestRunner = "de.mannodermaus.junit5:android-test-runner:${Versions.junit5TestRunner}"


        const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.fragmentTest}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

        const val axTestRules = "androidx.test:rules:${Versions.axTestRunRules}"

        val all = listOf(
            roboletric,
            spek,
            spekRunner,
            mockk,
            junit5,
            junit5Param,
            junit4,
            fragmentTest,
            coroutinesTest,
            axTestRules
        )
    }

    object CommonTest {
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
        const val kluent = "org.amshove.kluent:kluent-android:${Versions.kluent}"
        const val axEspresso = "androidx.test.espresso:espresso-core:${Versions.axEspresso}"
        const val axEspressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.axEspresso}"

        const val axJunit4 = "androidx.test.ext:junit:${Versions.axJunit4}"
        const val axTestRunner = "androidx.test:runner:${Versions.axTestRunRules}"
        const val axTestCore = "androidx.test:core:${Versions.coreKtx}"
        const val axTestKtx = "androidx.test:core-ktx:${Versions.coreKtx}"
        const val axArchTesting = "androidx.arch.core:core-testing:${Versions.axArchTesting}"

        val all = listOf(
            mockWebServer,
            kluent,
            axEspresso,
            axEspressoContrib,
            axTestCore,
            axTestKtx,
            axTestRunner,
            axJunit4,
            axArchTesting
        )
    }

}