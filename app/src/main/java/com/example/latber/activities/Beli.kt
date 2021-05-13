package com.example.latber.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.latber.DETAIL_ITEM
import com.example.latber.FAVORITE_IMAGE_URL
import com.example.latber.MyAlarmManager
import com.example.latber.R
import com.example.latber.data.Market_Item
import com.example.latber.service.InternalStorageDownloadService
import kotlinx.android.synthetic.main.activity_beli.*
import java.util.*

class beli : AppCompatActivity() {
    var mAlarmManager : AlarmManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beli)

        // membuat object untuk mengambil Parcelable dari class Market_Item dengan memasukkan key-nya
        // var item akan menampung data yang dikirim dari Market_Items_Adapter
        var item = this.intent.getParcelableExtra<Market_Item>(DETAIL_ITEM)
        // menampilkan data yang dikirim dari acitivity asal (Market_Items_Adapter)
        var img = findViewById<ImageView>(R.id.img_beli)
        Glide.with(this).load(item!!.imgs).into(img)
        findViewById<TextView>(R.id.keterangan).setText(item?.detail)
        findViewById<TextView>(R.id.harga).setText(item?.price.toString())

        Glide.with(this).load("https://iconarchive.com/download/i99352/dtafalonso/android-lollipop/Downloads.ico").into(save_to_internal)

        //Membentuk alarmManager
        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        setAlarm.setOnClickListener(){
            var alarmTimer  = Calendar.getInstance()
            alarmTimer.add(Calendar.SECOND, 5)

            var sendIntent = Intent(this, MyAlarmManager::class.java)
            sendIntent.putExtra(EXTRA_PESAN, "Pesan sekarang juga sebelum promo berakhir")
            var mPendingIntent = PendingIntent.getBroadcast(this, 101, sendIntent, 0)

            mAlarmManager?.set(AlarmManager.RTC, alarmTimer.timeInMillis, mPendingIntent)
            Toast.makeText(this, "Reminder telah dibuat", Toast.LENGTH_SHORT).show()
        }

        //disini ada changes
        //save to internal storage
        save_to_internal.setOnClickListener {
            val SaveInternalServiceIntent = Intent(this,InternalStorageDownloadService::class.java)
            SaveInternalServiceIntent.putExtra(FAVORITE_IMAGE_URL,item!!.imgs)

            InternalStorageDownloadService.enqueueWork(this,SaveInternalServiceIntent)
        }

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