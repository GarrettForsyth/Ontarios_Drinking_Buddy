package com.example.core_test

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CustomMatchers {

    /**
     * Creates matcher for view holder with given item view matcher.
     *
     * @param itemViewMatcher a item view matcher which is used to match item.
     * @return a matcher which matches a view holder containing item matching itemViewMatcher.
     */
    fun viewHolderMatcher(
        itemViewMatcher: Matcher<View>
    ): Matcher<RecyclerView.ViewHolder> {
        return object: TypeSafeMatcher<RecyclerView.ViewHolder>() {
            override fun describeTo(description: Description?) {
                description?.appendText("holder with view: ")
                itemViewMatcher.describeTo(description)
            }
            override fun matchesSafely(viewHolder: RecyclerView.ViewHolder?): Boolean {
                return itemViewMatcher.matches(viewHolder?.itemView)
            }
        }
    }

    /**
     * Matches a Matcher against the view holder in a particular position.
     */
    fun hasItemAtPosition(matcher: Matcher<View>, position: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return matcher.matches(viewHolder!!.itemView)
            }
        }
    }
}