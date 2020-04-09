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
    implementation("com.android.tools.build:gradle:4.1.0-alpha04")
}

kotlinDslPluginOptions{
    experimentalWarning.set(false)
}
