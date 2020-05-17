package com.example.core_test.testRules

import org.junit.rules.TestWatcher
import org.spekframework.spek2.dsl.Root

/**
 * Some extension functions to spek to give it
 * JUnit4 Rule like set up and tear down.
 */

private fun createAdapters(
    testWatchers: Array<out TestWatcher>,
    adapterSetup: (TestWatcherAdapter) -> Unit
) {
    testWatchers.forEach {
        adapterSetup.invoke(TestWatcherAdapter(it))
    }
}

fun Root.withTestRules(vararg testWatchers: TestWatcher) {
    createAdapters(testWatchers) {
        beforeEachTest {
            it.before()
        }
        afterEachTest {
            it.after()
        }
    }
}

fun Root.withGroupRules(vararg testWatchers: TestWatcher) {
    createAdapters(testWatchers) {
        beforeGroup {
            it.before()
        }
        afterGroup {
            it.after()
        }
    }
}
