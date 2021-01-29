package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_beli.*

class beli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beli)

        val bundle: Bundle = intent.extras!!
        val resId: Int = bundle.getInt("resId")
        img_beli.setImageResource(resId)

        var intentExtra = intent
        /* val bitmap = intent.getParcelableExtra("Bitmap") as Bitmap?

         img_beli.setImageBitmap(bitmap)*/

        keterangan.setText(intentExtra.getStringExtra(EXTRA_KETERANGAN))
        harga.setText(intentExtra.getStringExtra(EXTRA_HARGA))
    }

    var jlh = 1
    fun plus(view: View) {
        ++jlh
        jumlahbarang.setText(jlh.toString())
    }
    fun minus(view: View) {
        if (jlh<1)
            Toast.makeText(applicationContext,"Minimal Pemesanan adalah 1", Toast.LENGTH_SHORT).show()
        else{
            --jlh
            jumlahbarang.setText(jlh.toString())
        }
    }

/*    fun toMetode(view: View) {
        var intentReplay = Intent (this, metode::class.java)
        startActivity(intentReplay)
    }*/

    fun kehalamanPembayaran(view: View) {
        var intentReplay = Intent (this, metode::class.java)
        startActivity(intentReplay)
    }
}