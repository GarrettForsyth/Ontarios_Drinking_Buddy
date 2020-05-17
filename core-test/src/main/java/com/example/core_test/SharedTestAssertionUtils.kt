package com.example.core_test

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.core.databinding.LcboItemListItemBinding
import com.example.core.ui.DataBoundViewHolder
import com.example.core.vo.LCBOItem
import com.example.core_test.CustomMatchers.hasItemAtPosition
import com.example.core_test.CustomMatchers.viewHolderMatcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Assert.fail

/**
 * Scrolls down a recycler view and verifies its contents match that of the passed
 * in list of LCBOItems (order sensitive).
 *
 * @param recyclerViewId the id of the recyclerview to check
 * @param content the list of [LCBOItem] the recyclerview is expected to display.
 */
fun verifyRecyclerViewLCBOItemContents(recyclerViewId: Int, content: List<LCBOItem>) {
    content.forEachIndexed { position, expectedItem ->
        Espresso.onView(ViewMatchers.withId(recyclerViewId))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<DataBoundViewHolder<LcboItemListItemBinding>>(position)
            )
        Espresso.onView(ViewMatchers.withId(recyclerViewId))
            .check(
                ViewAssertions.matches(
                    // Check all items of interest are visible here!
                    allOf(
                        hasItemAtPosition(
                            hasDescendant(withText(expectedItem.name)),
                            position
                        )
                    )
                )
            )
    }
}


/**
 * Provides a RecyclerView assertion based on a view matcher. This allows you to
 * validate whether a RecyclerView contains a row in memory without scrolling the list.
 *
 * @param viewMatcher - an Espresso ViewMatcher for a descendant of any row in the recycler.
 * @return an Espresso ViewAssertion to check against a RecyclerView.
 */
fun withRowContaining(viewMatcher: Matcher<View>): ViewAssertion {
    return ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException
        if (BuildConfig.DEBUG && view !is RecyclerView) {
            error("Assertion failed")
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter!!
        (0..adapter.itemCount).forEach { position ->
            val itemType = adapter.getItemViewType(position)
            val viewHolder = adapter.createViewHolder(recyclerView, itemType)
            adapter.bindViewHolder(viewHolder, position)

            if (viewHolderMatcher(hasDescendant(viewMatcher)).matches(viewHolder)) {
                return@ViewAssertion
            }
        }
        fail("No match found")
    }
}
