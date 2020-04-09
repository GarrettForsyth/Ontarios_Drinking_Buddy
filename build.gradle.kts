import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.gradle)
        classpath(Plugins.kotlin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    apply {
        plugin(if (name == "app") "com.android.application" else "com.android.library")
        plugin("my-configuration-plugin")
        plugin("kotlin-android")
        plugin("kotlin-kapt")
        plugin("kotlin-android-extensions")
    }

    tasks.withType<Test> {
        testLogging {
            events(
                TestLogEvent.FAILED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT
            )
            ignoreFailures = true
            showCauses = true
            showStackTraces = true
            showExceptions = true
            exceptionFormat = TestExceptionFormat.FULL
        }
        addTestListener(object:  TestListener {
            override fun afterSuite(suite: TestDescriptor?, result: TestResult?) {
                if (suite?.parent == null) { // will match the outermost suite
                    val output = "Results: ${result?.resultType} (${result?.testCount} tests, ${result?.successfulTestCount} passed, ${result?.failedTestCount} failed, ${result?.skippedTestCount} skipped)"
                    val startItem = "|  "
                    val endItem = "  |"
                    val repeatLength = startItem.length + output.length + endItem.length
                    println('\n' + "-".repeat(repeatLength) + '\n' + startItem + output + endItem + '\n' + "-".repeat(repeatLength))
                }
            }
            override fun beforeTest(testDescriptor: TestDescriptor?) {}
            override fun beforeSuite(suite: TestDescriptor?) {}
            override fun afterTest(testDescriptor: TestDescriptor?, result: TestResult?) {}

        })
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}