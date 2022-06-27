package com.assignment.llyodesbanking

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
public class MainActivityTest  {


    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java,
        true,
        true
    )
    @Test
    fun testSampleRecyclerVisible() {
        onView(withId(R.id.rvVehicles)).inRoot(
            RootMatchers.withDecorView(
                Matchers.`is`(activityRule.getActivity().getWindow().getDecorView())
            )
        )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.rvVehicles)
        val itemCount = recyclerView.adapter!!.itemCount

        // Scroll to end of page with position
        onView(ViewMatchers.withId(R.id.rvVehicles))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

    @Test
    fun testCaseForRecyclerItemView() {
        onView(withId(R.id.rvVehicles)).inRoot(
            RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)))
            .check(matches(withViewAtPosition(1, Matchers.allOf(withId(R.id.tvVehName), isDisplayed()))))
    }

    fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}







