package com.example.latber.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.latber.DETAIL_ITEM
import com.example.latber.R
import kotlinx.android.synthetic.main.activity_beli.*

class beli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beli)

        // membuat object untuk mengambil Parcelable dari class Market_Item dengan memasukkan key-nya
        // var item akan menampung data yang dikirim dari Market_Items_Adapter
        var item = this.intent.getParcelableExtra<Market_Item>(DETAIL_ITEM)
        // menampilkan data yang dikirim dari acitivity asal (Market_Items_Adapter)
        findViewById<ImageView>(R.id.img_beli).setImageResource(item!!.imgs)
        findViewById<TextView>(R.id.keterangan).setText(item.detail)
        findViewById<TextView>(R.id.harga).setText(item.price.toString())


    }

    fun plus(view: View) {
        var jlh = jumlahbarang.text.toString().toInt()
        ++jlh
        jumlahbarang.setText(jlh.toString())
    }
    fun minus(view: View) {
        var jlh = jumlahbarang.text.toString().toInt()
        if (jlh<=1)
            Toast.makeText(applicationContext,"Minimal Pemesanan adalah 1", Toast.LENGTH_SHORT).show()
        else{
            --jlh
            jumlahbarang.setText(jlh.toString())
        }
    }


    fun kehalamanPembayaran(view: View) {
        var intentReplay = Intent (this, metode::class.java)
        startActivity(intentReplay)
        finish()

    }
}