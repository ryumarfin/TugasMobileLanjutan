package com.example.latber.activities

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.latber.R
import com.example.latber.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_metode.*
import kotlinx.android.synthetic.main.fragment_posting.*

class metode : AppCompatActivity() {
//    Deklarasi notification manager
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metode)
        notificationManager = NotificationManagerCompat.from(this)

        //Membuat action jika notifikasi di klik, maka akan dialihkan ke menu utama aplikasi
        val intent = Intent(this, menu::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        btnBayar1.setOnClickListener{
            //Membuat builder untuk membentuk notification
            val builder = NotificationCompat.Builder(this, notificationApp.CHANNEL_1)
                    .setSmallIcon(R.drawable.ic_baseline_done_24)
                    .setContentTitle("Cashback 10%")
                    .setContentText("Cashback telah ditambahkan ke saldo anda")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_PROMO)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            //Penggunaan jika membayar menggunakan saldo maka akan diberikan notifikasi cashback
            var intentReplay = Intent (this, done::class.java)
            if (cod.isChecked){
                startActivity(intentReplay)
                finish()
            }
            else if (saldo.isChecked){
                startActivity(intentReplay)
                val notification = builder.build()
                notificationManager.notify(1, notification)
                finish()
            }

            else
                Toast.makeText(this, "Pilih metode pembayaran", Toast.LENGTH_SHORT).show()

        }
    }
}