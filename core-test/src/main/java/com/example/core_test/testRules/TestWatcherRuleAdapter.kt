package com.example.core_test.testRules

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.isAccessible

class TestWatcherAdapter(private val testWatcher: TestWatcher) {
    fun before() {
        executeMethod("starting")
    }

    fun after() {
        executeMethod("finished")
    }

    private fun executeMethod(name: String) {
        val testWatcherClass = testWatcher::class
        val function = testWatcherClass.memberFunctions.first { it.name == name }
        function.isAccessible = true
        function.call(testWatcher, Description.EMPTY)
    }
}