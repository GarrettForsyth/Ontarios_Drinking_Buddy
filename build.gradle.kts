buildscript {
    repositories {
        google()
        jcenter()
        // TODO: This can be removed when Junit5 plugin is updated.
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
    dependencies {
        classpath(Plugins.gradle)
        classpath(Plugins.kotlin)
        classpath(Plugins.junit5)
        classpath(Plugins.safeArgs)
        classpath(Plugins.allOpen)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

// TODO: AllOpen only works when defined up here. Find out why
plugins {
    id("org.jetbrains.kotlin.plugin.allopen") version Versions.kotlin
}

subprojects {
    // Plugins common to all modules
    apply {
        plugin(if (name == "app") "com.android.application" else "com.android.library")
        plugin("kotlin-android")
        plugin("kotlin-kapt")
        plugin("kotlin-android-extensions")
        plugin("androidx.navigation.safeargs")
        plugin("de.mannodermaus.android-junit5")
        plugin("my-configuration-plugin")
        plugin("kotlin-allopen")
    }

    allOpen.annotation("com.example.core.annotations.OpenForTesting")

}

// Recommended using Dagger with Android Databinding. See https://github.com/google/dagger
gradle.projectsEvaluated {
    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xmaxerrs", "500"))
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}