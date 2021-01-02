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
//    var i = 0
    fun toRegisterPage(view: View) {
//       intent ekspilisit
//        ++i
//        var tv = findViewById<TextView>(R.id.et_email)
//        tv.text=i.toString()



        var intentRegister = Intent(this,Register::class.java)
        startActivity(intentRegister)

    }
}