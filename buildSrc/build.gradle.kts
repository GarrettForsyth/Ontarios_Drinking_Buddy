plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

gradlePlugin {
    plugins {
        create("MyPlugins") {
            id = "my-configuration-plugin"
            implementationClass = "MyConfigurationPlugin"
        }
    }
}

// TODO: Keep this dependency updated with latest version.
dependencies {
    implementation("com.android.tools.build:gradle:4.1.0-alpha09")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
}

kotlinDslPluginOptions{
    experimentalWarning.set(false)
}
