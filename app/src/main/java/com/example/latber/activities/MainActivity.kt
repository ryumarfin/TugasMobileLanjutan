package com.example.latber.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.latber.R
import com.example.latber.Register
import com.example.latber.airPlaneReceiver
import com.example.latber.sharePreferences.SharePrefHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

//nama file shared preference
private val PrefFileName = "MYFILEPREF01"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Melakukan register receiver di mainactivity
        var AirplaneReceiver = airPlaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirplaneReceiver, filter)

//        RememberMe.isChecked = true

        val f = File("/data/data/com.example.latber/shared_prefs/"+PrefFileName+".xml")
        //cek apakah file sharedpref exist
        if (f.exists()) {
            Log.d("TAG", PrefFileName + " exist")

            //inisialisasi
            var mySharedHelper = SharePrefHelper(this, PrefFileName)
            //mengambil data dari file sharedpref
            var email = mySharedHelper.email
            var pass = mySharedHelper.pass

            //cek apakah ada data yg tersimpan
            if (email != "" && pass != "" ){
                //membaca atau mengambil data dari SharePreferences
                et_email.setText(email)
                et_password.setText(pass)
            }
        }
        //jika file tidak ditemukan
        else
            Log.d("TAG", PrefFileName + " no exist")
    }


    fun toRegisterPage(view: View) {
        var intentRegister = Intent(this, Register::class.java)
        startActivity(intentRegister)

    }

    fun toMarketPage(view: View) {
        //cek apakah rememberme di centang
        if(RememberMe.isChecked()){
            //simpan dan panggil SharePrefHelper
            var mySharedHelper = SharePrefHelper(this, PrefFileName)
            //menyimpan email dan pass ke shareprefhelper
            mySharedHelper.email = et_email.text.toString()
            mySharedHelper.pass = et_password.text.toString()
        }

        var intentSparePart = Intent(this, menu::class.java)
        startActivity(intentSparePart)
        finish()

    }

    fun toMusic(view: View) {
        var intentRegister = Intent(this, MusicRoom::class.java)
        startActivity(intentRegister)

    }




}