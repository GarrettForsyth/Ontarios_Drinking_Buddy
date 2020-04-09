android {
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

}

dependencies {
    implementation(project(":browse"))
    implementation(Libs.appCompat)

    androidTestImplementation(project(path = ":core", configuration = "myAndroidTestConfiguration"))
}