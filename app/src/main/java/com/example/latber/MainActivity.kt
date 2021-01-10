package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun toRegisterPage(view: View) {
        var intentRegister = Intent(this,Register::class.java)
        startActivity(intentRegister)

    }

    fun toSparepartPage(view: View) {
        var intentSparePart = Intent(this,Sparepart::class.java)
        startActivity(intentSparePart)
        finish()
    }
}