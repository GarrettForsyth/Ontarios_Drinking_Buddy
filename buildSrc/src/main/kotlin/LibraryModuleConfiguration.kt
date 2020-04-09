import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion

/**
 * Configuration common to all library modules.
 */
fun LibraryExtension.configureLibrary() {
    setCompileSdkVersion(Config.compiledSdkVersion)
    defaultConfig.apply {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
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
