package com.example.latber

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.latber.activities.MainActivity
import org.hamcrest.core.AllOf
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
        //mengecek apakah tampilan MainActivity sesuai dengan yang diinginkan (sesuai dengan loginLayout)
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navToRegisterActivity() {
        //melakukan action click pada btn_register
        onView(withId(R.id.btn_register)).perform(click())
        //mengecek apakah tampilan yang muncul sesuai dengan yg diharapkan (sesuai dgn registerLayout)
        onView(withId(R.id.registerLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backPressToLoginActivity() {
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

    @Test
    fun test_loginValidation() {

        onView(withId(R.id.et_email)).perform(ViewActions.typeText("blackscreen"))
        onView(withId(R.id.et_password)).perform(ViewActions.typeText("123456"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.menuLayout)).check(matches((isDisplayed())))
    }

    @Test
    fun test_jumlahPembelian() {

        onView(withId(R.id.et_email)).perform(ViewActions.typeText("blackscreen"))
        onView(withId(R.id.et_password)).perform(ViewActions.typeText("123456"))
        onView(withId(R.id.btn_login)).perform(click())
        Espresso.onData(AllOf.allOf()).inAdapterView(withId(R.id.myGridView)).atPosition(2).perform(click())
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.jumlahbarang)).check(matches(withText("2")))

    }

    @Test
    fun test_postingItem() {
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.nav_Post)).perform(click())
        onView(withId(R.id.detailBarang)).perform(ViewActions.typeText("blackscreen"))
        pressBack()
        onView(withId(R.id.hargaBarang)).perform(ViewActions.typeText("100000"))
        pressBack()
        onView(withId(R.id.btn_post)).perform(click())
        onView(withId(R.id.detailBarangPost)).check(matches(withText("blackscreen")))
    }

    @Test
    fun test_metodePembayaran() {
        onView(withId(R.id.btn_login)).perform(click())
        Espresso.onData(AllOf.allOf()).inAdapterView(withId(R.id.myGridView)).atPosition(2).perform(click())
        onView(withId(R.id.tv2)).perform(click())
        onView(withId(R.id.btnBayar)).perform(click())

    }
}


