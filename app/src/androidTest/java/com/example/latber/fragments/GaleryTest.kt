package com.example.latber.fragments

import android.app.Activity
import android.app.Instrumentation.*
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.latber.R
import com.example.latber.activities.menu
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

class GaleryTest{
    //insialisasi intent espresso sblm pengujian (@Test) dimulai dan ditutup setelah pengujian selesai.
    @get: Rule
    val intentsTestRule = IntentsTestRule(menu::class.java)

    //mengcek intent camera atau apakah img dri hasil tangkapan camera berhasil dikirim ke imgview
    @Test
    fun test_GaleryIntent(){
        //panggil dan simpan fungsi createGallery
        val activityResult = createGallery()
        //mengambil action dan data yg diinginkna (dipassing)
        val expectedIntent : Matcher<Intent> = CoreMatchers.allOf(
            hasAction(Intent.ACTION_PICK),
            IntentMatchers.hasData(MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        )
        //merespon Intent yg diharapkan dgn hasil dari simulasi camera yg dibuat tadi
        intending(expectedIntent).respondWith(activityResult)
        //click navigation post
        onView(withId(R.id.nav_Post)).perform(click())
        //click btn addImage
        onView(withId(R.id.addimage)).perform(click())
        //click btn galelry yg ad dlm AlertDialog
        onView(withText("GALERY")).perform(click())
        //validasi expectedIntent.
        intending(expectedIntent)
    }

    //karena tdk dpt dipastikan apakah gambar yg diinginkan ada pada galery,
    //maka buat simulasi pengambilan gambar dar galery
    private fun createGallery(): ActivityResult? {
        //pengambilan alamat resource gambar
        val resources: Resources = intentsTestRule.activity.resources
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(R.drawable.pic1) + "/" +
                    resources.getResourceTypeName(R.drawable.pic1) + "/" +
                    resources.getResourceEntryName(R.drawable.pic1))
        val resultIntent = Intent()
        resultIntent.setData(imageUri)
        //mengirimkan hasil pengambilan gambar
        return ActivityResult(Activity.RESULT_OK, resultIntent)
    }
}