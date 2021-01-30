package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_metode.*

class metode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metode)
    }
    fun toDone(view: View) {
        var intentReplay = Intent (this, done::class.java)
        if (cod.isChecked || saldo.isChecked){
            startActivity(intentReplay)
            finish()
        }
        else
            Toast.makeText(this, "Pilih metode pembayaran", Toast.LENGTH_SHORT).show()
    }
}