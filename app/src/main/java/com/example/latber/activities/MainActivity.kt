package com.example.latber.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.latber.R
import com.example.latber.Register
import com.example.latber.airPlaneReceiver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Melakukan register receiver di mainactivity
        var AirplaneReceiver = airPlaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirplaneReceiver, filter)


    }


    fun toRegisterPage(view: View) {
        var intentRegister = Intent(this, Register::class.java)
        startActivity(intentRegister)

    }

    fun toSparepartPage(view: View) {
        var intentSparePart = Intent(this, menu::class.java)
        startActivity(intentSparePart)
        finish()
    }




}