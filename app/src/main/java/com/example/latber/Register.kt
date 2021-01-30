package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun RegistertoSparepartPage1(view: View) {
        var intentSparePart = Intent(this,menu::class.java)
        startActivity(intentSparePart)
        finish()
    }
}