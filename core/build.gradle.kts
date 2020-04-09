configurations {
    create("myTestConfiguration").extendsFrom(
        configurations.getByName("testImplementation"))
    create("myAndroidTestConfiguration").extendsFrom(
        configurations.getByName("androidTestImplementation"))
}
dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)

    testImplementation(Libs.junit4)
    androidTestImplementation(Libs.axJunit4)
    androidTestImplementation(Libs.axEspresso)
}