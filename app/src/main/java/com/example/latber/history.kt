package com.example.latber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import java.util.*

class history : AppCompatActivity() {
    private lateinit var myAdapter: historyAdapter
    private var myhistory : MutableList<Riwayat> = mutableListOf(
        Riwayat(Layanan = "Panggil Montir",Keperluan = "Service Motor", Date(9-12-2020),harga = 105000),
        Riwayat(Layanan = "Panggil Bensin",Keperluan = "15 Liter", Date(9-12-2020),harga = 254000),
        Riwayat(Layanan = "Sparepart",Keperluan = "Kunci kontak vespa", Date(9-12-2020),harga = 85000)

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        myAdapter = historyAdapter(myhistory)

        Myrecycleview.adapter = myAdapter
        Myrecycleview.layoutManager=LinearLayoutManager(this)
    }
}