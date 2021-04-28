package com.example.latber

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.latber.activities.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentTest {
    //menjalankan MainActivity sebelum pengujian (@Test) dimulai dan ditutup setelah pengujian.
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun FragmentNav() {
        //melakukan action click pada btn_login
        onView(withId(R.id.btn_login)).perform(click())
        //mengecek apakah tampilan yang muncul sesuai dengan yg diharapkan (sesuai dgn menuLayout)
        onView(withId(R.id.menuLayout)).check(matches(isDisplayed()))

        onData(allOf()).inAdapterView(withId(R.id.myGridView)).atPosition(2).perform(click())
//        onView(withId(R.id.item_sparepare)).perform(click())

//        onView(withId(R.id.beliLayout)).check(matches(isDisplayed()))
    }
}