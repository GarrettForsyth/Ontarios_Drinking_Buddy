android.buildFeatures.dataBinding = true

dependencies {
    implementation(project(":core"))
    implementation(project(":browse"))

    implementation(Libs.appCompat)
    implementation(Libs.Ui.materialDesign)
    Libs.Network.all.forEach { source -> implementation(source) }

    androidTestImplementation(project(":core-test"))
    androidTestImplementation(Libs.Ui.materialDesign)
    androidTestImplementation(Libs.axEspressoIdlingResource)
    Libs.CommonTest.all.forEach{ source -> androidTestImplementation(source) }

    kapt(Libs.Kapt.daggerCompiler)
    kapt(Libs.Kapt.daggerAndroidSupportCompiler)
    kaptAndroidTest(Libs.Kapt.daggerCompiler)
    kaptAndroidTest(Libs.Kapt.daggerAndroidSupportCompiler)
}
