package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_beli.*

class panggilbensin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panggilbensin)
    }

    var jlh = 1
    fun plus(view: View) {
        ++jlh
        jumlahbarang.setText(jlh.toString())
    }
    fun minus(view: View) {
        if (jlh<2)
            Toast.makeText(applicationContext,"Minimal Pemesanan adalah 1", Toast.LENGTH_SHORT).show()
        else{
            --jlh
            jumlahbarang.setText(jlh.toString())
        }
    }

    fun toMetode(view: View) {
        var intentReplay = Intent (this, metode::class.java)
        startActivity(intentReplay)
    }

    fun toSparepartPage(view: View) {
        var intentReplay = Intent (this, Sparepart::class.java)
        startActivity(intentReplay)
    }
}