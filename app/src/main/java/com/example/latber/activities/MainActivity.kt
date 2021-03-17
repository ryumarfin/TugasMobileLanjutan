package com.example.latber.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.latber.R
import com.example.latber.Register

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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