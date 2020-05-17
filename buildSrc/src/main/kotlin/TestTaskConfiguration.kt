import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.tasks.testing.logging.TestLoggingContainer

fun Test.configureTests() {
    useJUnitPlatform {
            includeEngines("spek2")
    }

    testLogging {
        configureTestLogging()
        ignoreFailures = true
    }

    addTestListener(object : TestListener {
        override fun afterSuite(suite: TestDescriptor?, result: TestResult?) {
            displayResults(suite, result)
        }

        override fun beforeTest(testDescriptor: TestDescriptor?) {}
        override fun beforeSuite(suite: TestDescriptor?) {}
        override fun afterTest(testDescriptor: TestDescriptor?, result: TestResult?) {}
    })
}

private fun TestLoggingContainer.configureTestLogging() {
    events(
        TestLogEvent.FAILED,
        TestLogEvent.PASSED,
        TestLogEvent.SKIPPED,
        TestLogEvent.STANDARD_OUT
    )
    showCauses = true
    showStackTraces = true
    showExceptions = true
    exceptionFormat = TestExceptionFormat.FULL
}

private fun displayResults(
    suite: TestDescriptor?,
    result: TestResult?
) {
    if (suite?.parent == null) { // will match the outermost suite
        val output =
            "Results: ${result?.resultType} (${result?.testCount} tests, ${result?.successfulTestCount} passed, ${result?.failedTestCount} failed, ${result?.skippedTestCount} skipped)"
        val startItem = "|  "
        val endItem = "  |"
        val repeatLength = startItem.length + output.length + endItem.length
        println(
            '\n' + "-".repeat(repeatLength) + '\n' + startItem + output + endItem + '\n' + "-".repeat(
                repeatLength
            )
        )
    }
}
