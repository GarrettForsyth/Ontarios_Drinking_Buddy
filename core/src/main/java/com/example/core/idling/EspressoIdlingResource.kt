package com.example.core.idling

import androidx.test.espresso.IdlingResource


/**
 * Contains a static reference IdlingResource, and should be available only in a mock build type.
 */
object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val mCountingIdlingResource =
        SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource
}