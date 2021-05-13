package com.example.latber.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.latber.R

class dapatMontir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dapat_montir)
    }

    fun toRating(view: View) {
        var intentSparePart = Intent(this, rating::class.java)
        startActivity(intentSparePart)
        finish()
    }

}