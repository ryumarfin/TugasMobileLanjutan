package com.example.latber

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.latber.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ActivityTest{

    //menjalankan MainActivity sebelum pengujian (@Test) dimulai dan ditutup setelah pengujian.
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isActivityInView() {
        //Menjalakan MAinActivity
//        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //mengecek apakah tampilan MainActivity sesuai dengan yang diinginkan (sesuai dengan loginLayout)
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navToRegisterActivity() {
        //menjalankan MainActivity
//        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //melakukan action click pada btn_register
        onView(withId(R.id.btn_register)).perform(click())
        //mengecek apakah tampilan yang muncul sesuai dengan yg diharapkan (sesuai dgn registerLayout)
        onView(withId(R.id.registerLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backPressToLoginActivity() {
        //menjalankan MainActivity
//        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //melakukan action click pada btn_register
        onView(withId(R.id.btn_register)).perform(click())
        //mengecek apakah tampilan yang muncul sesuai dengan yg diharapkan (sesuai dgn registerLayout)
        onView(withId(R.id.registerLayout)).check(matches(isDisplayed()))
        //lakukan backpress
        pressBack()
        //mengecek apakah akan kembali ke activity parentnya / activity sebelumnya atau
        //mengecek apakah tampilan setelah backpress adalah loginLayout
        onView(withId(R.id.loginLayout)).check(matches((isDisplayed())))
    }
}


