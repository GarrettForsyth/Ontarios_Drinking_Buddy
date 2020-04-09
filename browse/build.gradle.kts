dependencies {
    implementation(project(":core"))
    testImplementation(project(path= ":core", configuration =  "myTestConfiguration"))
    androidTestImplementation(project(path = ":core", configuration = "myAndroidTestConfiguration"))
}