package com.example.browse.ui

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.browse.R
import com.example.browse.TestApp
import com.example.core.vo.LCBOItem
import com.example.core.vo.Resource
import com.example.core_test.listOfLcboItems
import com.example.core_test.ui.util.ViewModelUtil
import com.example.core_test.verifyRecyclerViewLCBOItemContents
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.random.Random

@MediumTest
@RunWith(AndroidJUnit4::class)
@Config(application = TestApp::class, sdk = [Build.VERSION_CODES.O_MR1])
class BrowseFragmentTest {

    private lateinit var scenario: FragmentScenario<IsolatedBrowseFragment>

    @Before
    fun launchBrowseFragment() {
        // GIVEN - the BrowseFragment is launched
        scenario = launchFragmentInContainer<IsolatedBrowseFragment>(
            null, R.style.AppTheme, IsolatedBrowseFragmentFactory()
        )
    }

    @Test
    fun `Search results are observed`() {
        scenario.onFragment {
            verify(exactly = 1) { it.viewmodel.searchResults }
        }
    }

    @Test
    fun `When the BrowseFragment is launched it should display initial views`() {
        // THEN - initial views are displayed
        onView(withId(R.id.browse_query_edit_text))
        onView(withId(R.id.browse_query_button))
    }

    @Test
    fun `When a keystroke occurs in the query editText it should update the viewmodel`() {
        // WHEN - The word 'dogs' is entered into the editText
        val filterString = "dogs"
        onView(withId(R.id.browse_query_edit_text)).perform(typeText(filterString))

        // THEN - the viewmodel is updated for each keystroke
        scenario.onFragment {
            verify {
                it.viewmodel.queryParameters.filterString = "d"
                it.viewmodel.queryParameters.filterString = "do"
                it.viewmodel.queryParameters.filterString = "dog"
                it.viewmodel.queryParameters.filterString = "dogs"
            }
        }
    }

    @Test
    fun `When the search button is pressed it should call search on the viewmodel`() {
        // WHEN - the search button is pressed
        onView(withId(R.id.browse_query_button)).perform(click())
        // THEN - the viewmodel calls search
        scenario.onFragment {
            verify { it.viewmodel.search() }
        }
    }

    @Test
    fun `When results are available it should display the results in a list`() {
        // GIVEN - the viewmodel is updated with search results
        val numberOfSearchResults = Random.nextInt(1, 20)
        val searchResults = Resource.success(listOfLcboItems(numberOfSearchResults))
        scenario.onFragment {
            it.fakeSearchResults.value = searchResults
        }

        // THEN - the search results are updated in the recyclerview
        verifyRecyclerViewLCBOItemContents(R.id.browse_search_results_list, searchResults.data!!)
    }
}

/**
 * A [BrowseFragment] with all dependencies mocked or faked.
 */
class IsolatedBrowseFragment : BrowseFragment() {
    val fakeSearchResults = MutableLiveData<Resource<List<LCBOItem>>>()
}

class IsolatedBrowseFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return (super.instantiate(classLoader, className) as IsolatedBrowseFragment).apply {
            viewmodel = mockk(relaxed = true)
            viewModelFactory = ViewModelUtil.createFor(viewmodel)
            every { viewmodel.searchResults } returns fakeSearchResults
        }
    }
}