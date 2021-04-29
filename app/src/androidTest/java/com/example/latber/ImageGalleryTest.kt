package com.example.latber

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.app.Instrumentation.*
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.latber.activities.MainActivity
import com.example.latber.activities.menu
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ImageGalleryTest {
    //menjalankan MainActivity sebelum pengujian (@Test) dimulai dan ditutup setelah pengujian.
    @get: Rule
//    val activityRule = ActivityScenarioRule(menu::class.java)

    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    fun ValidasiGalleriIntent(){
        //melakukan action click pada btn_login
//        onView(withId(R.id.btn_login)).perform(click())





        //mengambil action dan data yg diinginkna (dipassing)
//        val expectedIntent : Matcher<Intent> = allOf(hasAction(Intent.ACTION_PICK), hasData(MediaStore.Images.Media.INTERNAL_CONTENT_URI))
//        //memanggil dan menyimpan hasil dari createGallery()
//        val activityResult = createGallery()
//        //membandnigkan intent yg diharapkan dgn hasil yg di uji melalui simulasi pengambilan gambar
//        intending(expectedIntent).respondWith(activityResult)
//
//        onView(withId(R.id.addimage)).perform(click())
//        onView(withText("GALERY")).perform(click())
//        intended(expectedIntent)
//        performClick(dialog.getButton(DialogInterface.BUTTON_POSITIVE));
    }

    //karena tidak dapat mengambil data dari gallery, maka
    //buat simulasi pengambilan gambar
    private fun createGallery(): ActivityResult{
        val resources:Resources = InstrumentationRegistry.getInstrumentation().context.resources
        val imageUri = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        resources.getResourcePackageName(R.drawable.pic1) + "/" +
                        resources.getResourceTypeName(R.drawable.pic1) + "/" +
                        resources.getResourceEntryName(R.drawable.pic1))
        val resultIntent = Intent()
        resultIntent.setData(imageUri)
        //mengirimkan hasil pengambilan gambar
        return ActivityResult(RESULT_OK, resultIntent)
    }
}