package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*

//2. pembuatan extra status
private const val EXTRA_STATUS = "ExtraStatus"

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

    fun cek(view: View) {

        statusMail.setText( " " + et_Email_Register.text + "   Belum terdaftar")
    }

    //1. Pembuatan save instance state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATUS, statusMail.text.toString())
    }

    //3. pembacaan saveinstance state
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        statusMail.text =   savedInstanceState?.getString(EXTRA_STATUS) ?: "-"
    }
}