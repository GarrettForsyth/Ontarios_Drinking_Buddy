android.buildFeatures.dataBinding = true

dependencies {
    implementation(project(":core"))
    testImplementation(project(":core-test"))

    testImplementation(project(path = ":core-test", configuration = "myTestConfiguration"))
    androidTestImplementation(
        project(
            path = ":core-test",
            configuration = "myAndroidTestConfiguration"
        )
    )

    implementation(Libs.Ui.constraintLayout)
    Libs.Lifecycle.all.forEach { source -> implementation(source) }

    kapt(Libs.Kapt.daggerCompiler)
    kapt(Libs.Kapt.daggerAndroidSupportCompiler)
    kapt(Libs.Kapt.axLifecycleCompiler)
    kaptTest(Libs.Kapt.databindingCompiler) // This is needed to run test via ./gradlew test

    testRuntimeOnly(Libs.RunTime.junit5Engine)
    testRuntimeOnly(Libs.RunTime.junitVintageEngine)
//    testRuntimeOnly(Libs.Kotlin.kotlinReflect)
//    testRuntimeOnly(Libs.RunTime.spekRunner)
}
