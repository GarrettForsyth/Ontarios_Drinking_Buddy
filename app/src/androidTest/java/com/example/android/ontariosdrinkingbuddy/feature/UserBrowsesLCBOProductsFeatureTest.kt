package com.example.android.ontariosdrinkingbuddy.feature

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.android.ontariosdrinkingbuddy.MainActivity
import com.example.android.ontariosdrinkingbuddy.ODBTestApp
import com.example.android.ontariosdrinkingbuddy.R
import com.example.android.ontariosdrinkingbuddy.util.DataBindingIdlingResourceRule
import com.example.core_test.MockWebServerResponseUtils
import com.example.core.idling.EspressoIdlingResource
import com.example.core_test.withRowContaining
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

/**
 *  FEATURE TEST
 *
 *  As someone who enjoys to drink
 *  I want to browse the LCBO's inventory
 *  So I can find information on new drinks.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class UserBrowsesLCBOProductsFeatureTest {

    // GIVEN - the user has just opened the app
    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    @get:Rule
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(scenarioRule)

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        val testApp = ApplicationProvider.getApplicationContext<ODBTestApp>()
        mockWebServer = testApp.appComponent.getMockWebServer()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun userBrowsesForBeerStory() {
        // GIVEN - the sever will return the contents of search_beer.json
        MockWebServerResponseUtils.enqueueServerWithFile(mockWebServer, "search_beer.json")

        // When - I Type 'beer' into the edit text
        onView(withId(R.id.browse_query_edit_text))
            .perform(typeText("beer"))

        // And - I click the query button
        onView(withId(R.id.browse_query_button))
            .perform(click())
        countingTaskExecutorRule.drainTasks(3, TimeUnit.SECONDS) // Wait for live data

        // THEN - I should see a list of items filtered by my query
        val expectedItems =
            MockWebServerResponseUtils.lcboItemResponseToList("expected_beer_results.json")
        expectedItems.forEach { item ->
            onView(withId(R.id.browse_search_results_list))
                .check(withRowContaining(withText(item.name)))
        }
    }
}