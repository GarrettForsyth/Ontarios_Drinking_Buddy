android.buildFeatures.dataBinding = true

configurations {
    create("myTestConfiguration").extendsFrom(
        findByName("testImplementation"))
    create("myAndroidTestConfiguration").extendsFrom(
        findByName("androidTestImplementation"))
}

dependencies {
    implementation(project(":core"))
    implementation(Libs.Kotlin.kotlinReflect)
    implementation(Libs.Ui.recyclerView)
    implementation(Libs.Test.spek)
    implementation(Libs.CommonTest.axEspresso)
    implementation(Libs.CommonTest.axEspressoContrib)
    implementation(Libs.Test.coroutinesTest)
    implementation(Libs.Test.mockk)
    implementation(Libs.Network.gsonConverter)
    implementation(Libs.Network.gson)
    implementation(Libs.CommonTest.mockWebServer)

    testImplementation(Libs.Kotlin.kotlinJdk8)
    Libs.Test.all.forEach { source -> testImplementation(source) }
    Libs.CommonTest.all.forEach { source -> testImplementation(source) }
}