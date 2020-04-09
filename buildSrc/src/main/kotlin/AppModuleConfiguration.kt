import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion

/**
 * Configuration for the application module.
 */
fun AppExtension.configureLibrary() {
    setCompileSdkVersion(Config.compiledSdkVersion)
    defaultConfig.apply {
        applicationId(Config.applicationId)
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode(Config.versionCode)
        versionName(Config.versionName)
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        findByName("test")?.java?.srcDir(sharedTestDir)
        findByName("androidTest")?.java?.srcDir(sharedTestDir)
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

}
