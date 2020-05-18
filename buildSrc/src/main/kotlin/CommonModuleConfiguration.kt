import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion

/**
 * Configuration common between
 *      AppExtension
 *      LibraryExtension
 *      TestExtension
 *      FeatureExtension
 */
fun BaseExtension.configureCommon() {
    setCompileSdkVersion(Config.compiledSdkVersion)
    defaultConfig.apply {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        testInstrumentationRunner("com.example.android.ontariosdrinkingbuddy.ODBTestRunner")
    }

    flavorDimensions("default")
    productFlavors {
        create("dev") {
            resConfigs("en", "xxhdpi")
        }
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

    lintOptions {
        isAbortOnError = false
        disable(
            "UnsafeExperimentalUsageError",
            "UnsafeExperimentalUsageWarning"
        )
    }
}