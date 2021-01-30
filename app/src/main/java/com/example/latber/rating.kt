package com.example.latber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
    }

    fun selesai(view: View) {
        finish()
    }
}