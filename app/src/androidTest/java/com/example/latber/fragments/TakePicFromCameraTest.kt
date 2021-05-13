package com.example.latber.fragments

import android.app.Activity
import android.app.Instrumentation.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.latber.R
import com.example.latber.activities.menu
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

class TakePicFromCameraTest{
    //insialisasi intent espresso sblm pengujian (@Test) dimulai dan ditutup setelah pengujian selesai.
    @get: Rule
    val intentsTestRule = IntentsTestRule(menu::class.java)

    //mengcek intent camera atau apakah img dri hasil tangkapan camera berhasil dikirim ke imgview
    @Test
    fun test_cameraIntent(){
        //panggil dan simpan fungsi picImageActivityResult
        val activityResult = picImageActivityResult()
        //simpan action diinginkan yaitu utk mengambil gambar melalui camera
        val expectedIntent:Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        //merespon Intent yg diharapkan dgn hasil dari simulasi camera yg dibuat tadi
        intending(expectedIntent).respondWith(activityResult)
        //click navigation post
        onView(withId(R.id.nav_Post)).perform(click())
        //click btn addImage
        onView(withId(R.id.addimage)).perform(click())
        //click btn camera yg ad dlm AlertDialog
        onView(withText("CAMERA")).perform(click())
        //validasi expectedIntent.
        intending(expectedIntent)
    }

    //karena tdk dpt dipastikan apakah gambar yg diinginkan ada pada stiap device,
    //maka buat simulasi pengambilan gambar
    //disni kita hanya akan mengecek apakah intentcamera memberikan hasil yg diharapkan
    private fun picImageActivityResult(): ActivityResult? {
        val bundle = Bundle()
        //input key img
        bundle.putParcelable("data", BitmapFactory.decodeResource(
            //ambil gambar yg ada pada folder drawable
            intentsTestRule.activity.resources, R.drawable.ic_launcher_background
        ))
        val resultData = Intent()
        resultData.putExtras(bundle)
        //kembalikan hasil dri simulasi intent camera yaitu
        //resultCode dan bundle data
        return ActivityResult(Activity.RESULT_OK, resultData)
    }
}