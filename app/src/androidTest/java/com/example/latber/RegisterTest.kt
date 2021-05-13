package com.example.latber

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.latber.activities.MainActivity
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RegisterTest {
    //menjalankan Register sebelum pengujian (@Test) dimulai
    @get :Rule
    val activityRule = ActivityTestRule(Register::class.java)

    //pengujian inputan user pada form register

    @Test
    fun test_textRegistrasi_AllEmpty(){
        //click btn register tanpa melakukan pengisian form
        onView(withId(R.id.btn_Register)).perform(click())
        //pengecekan message toast yg muncul sudah sesuai dgn yg diinginkan
        onView(withText("Please fill your First Name"))
            .inRoot(withDecorView(not(`is`(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_FillFirstName(){
        //isi editText First Name
        onView(withId(R.id.et_firstname_Register)).perform(typeText("Ryu"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //click btn register
        onView(withId(R.id.btn_Register)).perform(click())
        //pengecekan message toast yg muncul sudah sesuai dgn yg diinginkan
        onView(withText("Please fill your Last Name"))
            .inRoot(withDecorView(not(`is`(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_Fill_LastName(){
        //isi editText First Name
        onView(withId(R.id.et_firstname_Register)).perform(typeText("Ryu"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //isi editText Last Name
        onView(withId(R.id.et_lastname_Register)).perform(typeText("Marfin"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //click btn register
        onView(withId(R.id.btn_Register)).perform(click())
        //pengecekan message toast yg muncul sudah sesuai dgn yg diinginkan
        onView(withText("Please fill your email"))
            .inRoot(withDecorView(not(`is`(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_Fill_Email(){
        //isi editText First Name
        onView(withId(R.id.et_firstname_Register)).perform(typeText("Ryu"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard
        //isi editText Last Name
        onView(withId(R.id.et_lastname_Register)).perform(typeText("Marfin"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //isi editText Email dgn format yg salah
        onView(withId(R.id.et_Email_Register)).perform(typeText("ryumarfin"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //click btn register
        onView(withId(R.id.btn_Register)).perform(click())
        //pengecekan message toast yg muncul sudah sesuai dgn yg diinginkan
        onView(withText("Email is Invalid!"))
            .inRoot(withDecorView(not(`is`(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_Fill_Password(){
        //isi editText First Name
        onView(withId(R.id.et_firstname_Register)).perform(typeText("Ryu"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard
        //isi editText Last Name
        onView(withId(R.id.et_lastname_Register)).perform(typeText("Marfin"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard
        //isi editText Email dgn format yg benar
        onView(withId(R.id.et_Email_Register)).perform(typeText("ryumarfin@gmail.com"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //isi editText Password
        onView(withId(R.id.et_Password_Register)).perform(typeText("123abc"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //click btn register
        onView(withId(R.id.btn_Register)).perform(click())
        //pengecekan message toast yg muncul sudah sesuai dgn yg diinginkan
        onView(withText("Please fill your Confirm Password"))
            .inRoot(withDecorView(not(`is`(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_Fill_ConfirmPassword(){
        //isi editText First Name
        onView(withId(R.id.et_firstname_Register)).perform(typeText("Ryu"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard
        //isi editText Last Name
        onView(withId(R.id.et_lastname_Register)).perform(typeText("Marfin"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard
        //isi editText Email dgn format yg benar
        onView(withId(R.id.et_Email_Register)).perform(typeText("ryumarfin@gmail.com"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard
        //isi editText Password
        onView(withId(R.id.et_Password_Register)).perform(typeText("123abc"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //isi editText Confirm Password yg SALAH
        onView(withId(R.id.et_confirmPassword_Register)).perform(typeText("123"))
            .perform(closeSoftKeyboard()) //menutup soft keyboard

        //click btn register
        onView(withId(R.id.btn_Register)).perform(click())
        //pengecekan message toast yg muncul sudah sesuai dgn yg diinginkan
        onView(withText("Confirm Password is wrong"))
            .inRoot(withDecorView(not(`is`(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }
}