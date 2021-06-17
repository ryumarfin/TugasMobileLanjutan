package com.example.latber.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.latber.DETAIL_ITEM
import com.example.latber.FAVORITE_IMAGE_URL
import com.example.latber.MyAlarmManager
import com.example.latber.R
import com.example.latber.data.Market_Item
import com.example.latber.service.InternalStorageDownloadService
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_beli.*
import java.util.*


class beli : AppCompatActivity() {
    var mAlarmManager : AlarmManager? = null

    //inisialisasi var mInterAds
    var mInterAds : InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beli)

        //Load Interstitial Ads
        loadAd()

        // membuat object untuk mengambil Parcelable dari class Market_Item dengan memasukkan key-nya
        // var item akan menampung data yang dikirim dari Market_Items_Adapter
        var item = this.intent.getParcelableExtra<Market_Item>(DETAIL_ITEM)
        // menampilkan data yang dikirim dari acitivity asal (Market_Items_Adapter)
        var img = findViewById<ImageView>(R.id.img_beli)
        Glide.with(this).load(item!!.imgs).into(img)
        findViewById<TextView>(R.id.keterangan).setText(item?.detail)
        findViewById<TextView>(R.id.harga).setText(item?.price.toString())

        Glide.with(this).load("https://iconarchive.com/download/i99352/dtafalonso/android-lollipop/Downloads.ico").into(
            save_to_internal
        )

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
            //memunculkan Interstitial Ads ketika user melakukan klik utk menyimpan gambar
            showInterstitial()

            //mulai JobIntentService "InternalStorageDownloadService" dengan membentuk Intent dan
            //kemudian daftarkan InternalStorageDownloadService pada antrian menggunakan enqueueWork() untuk memulainya
            val SaveInternalServiceIntent = Intent(this, InternalStorageDownloadService::class.java)
            SaveInternalServiceIntent.putExtra(FAVORITE_IMAGE_URL, item!!.imgs)

            InternalStorageDownloadService.enqueueWork(this, SaveInternalServiceIntent)
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
            Toast.makeText(applicationContext, "Minimal Pemesanan adalah 1", Toast.LENGTH_SHORT).show()
        else{
            --jlh
            jumlahbarang.setText(jlh.toString())
        }
    }


    fun kehalamanPembayaran(view: View) {
        var intentReplay = Intent(this, metode::class.java)
        startActivity(intentReplay)
        finish()

    }

    //fungsi ini berfungsi untuk memuat iklan
    fun loadAd(){
        //inisialisasi adRequest agar dapat mengambil iklan dari google ad manager.
        var adRequest = AdRequest.Builder().build()
        //setelah itu, untuk memuat iklan, kita panggil load dan kirimkan parameter yg dibutuhkan.
        //InterstitialAdLoadCallback akan digunakan untuk menerima iklan yang berhasil dimuat atau menerima error
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712",
            adRequest, object : InterstitialAdLoadCallback() {
                //ketika iklan gagal di Load, function ini akan dijalankan
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Log.d("Interstitial Ads", p0?.message)
                    Toast.makeText(this@beli, "Iklan gagal di Load", Toast.LENGTH_SHORT).show()
                    //karena iklan gagal dimuat, maka kita kembalikan null
                    mInterAds = null
                }

                //ketika iklan berhasil di Load, function ini akan dijalankan
                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    Log.d("Interstitial Ads", "Interstitial Ads berhasil di Load")
                    //kembalikan iklan yang berhasil dimuat untuk ditampilkan nantinya
                    mInterAds = p0
                }
            })
    }

    //fungsi utk menampilkan iklan
    private fun showInterstitial() {
        //lakukan pengeeckan terlebih dahulu apakah ada iklan yang dapat di tampilkan
        if (mInterAds != null) {
            //buat FullScreenContentCallback untuk dipanggil ketika iklan ditampilkan dan ditutup
            mInterAds?.fullScreenContentCallback = object : FullScreenContentCallback() {
                //ketika user menutup iklan
                override fun onAdDismissedFullScreenContent() {
                    Log.d("Interstitial Ads", "Ad was dismissed.")
                    //jangan lupa mengganti mInterAds menjadi null agar iklan tidak ditampilakn 2x
                    mInterAds = null
                    //setelah itu, jangan lupa utk memuat iklan baru lagi
                    loadAd()
                }
                //ketika iklan gagal ditampilkan
                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("Interstitial Ads", "Ad failed to show.")
                    //jgn lupa mengganti mInterAds menjadi null agar iklan tidak ditampilakn lagi
                    mInterAds = null
                }
                //ketika iklan  ditampilkan
                override fun onAdShowedFullScreenContent() {
                    Log.d("Interstitial Ads", "Ad showed fullscreen content.")
                }
            }
            //tampilkan interstitial ads nya
            mInterAds?.show(this)
        }
        else {
            Log.d("Interstitial Ads", "Ad wasn't loaded.")
//            Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
            //karena tidak ada iklan, maka kita akan memuat iklan baru
            loadAd()
        }
    }
}