android.buildFeatures.dataBinding = true

android {
    defaultConfig {
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas".toString())
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
            }
        }
    }
}

// Gets rid of gradle warning. See https://github.com/antlr/antlr4/issues/1782
configurations.all() {
    resolutionStrategy {
        force(
            "org.antlr:antlr4-runtime:4.5.3",
            "org.antlr:antlr4-tool:4.5.3"
        )
    }
}

dependencies {
    implementation(Libs.Kotlin.kotlin)
    Libs.Network.all.forEach { source -> implementation(source) }
    Libs.Ui.all.forEach { source -> implementation(source) }


    api(Libs.Kotlin.coreKtx)
    api(Libs.axEspressoIdlingResource)
    api(Libs.timber)
    Libs.Room.all.forEach { source -> api(source)}
    Libs.Dagger.all.forEach { source -> api(source) }
    Libs.Navigation.all.forEach { source -> api(source) }

    kapt(Libs.Kapt.daggerCompiler)
    kapt(Libs.Kapt.daggerAndroidSupportCompiler)
    kapt(Libs.Kapt.roomCompiler)

    testImplementation(project(":core-test"))
    testImplementation(project(path = ":core-test", configuration = "myTestConfiguration"))
}