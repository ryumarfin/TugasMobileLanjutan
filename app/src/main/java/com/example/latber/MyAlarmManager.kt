package com.example.latber

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.latber.activities.EXTRA_PESAN
import com.example.latber.activities.beli
import com.example.latber.activities.menu

class MyAlarmManager : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val NotifyId = 30103
        val Channel_id = "channel_01"
        val name = "ON/OFF"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val nNotifyChannel = NotificationChannel(Channel_id, name, importance)
        var mbuilder = NotificationCompat.Builder(context, Channel_id)
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setContentText(intent?.getStringExtra(EXTRA_PESAN)?:"Tidak ada pesan")
                .setContentTitle("Pesan Sekarang")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        var mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        for (s in mNotificationManager.notificationChannels){
            mNotificationManager.deleteNotificationChannel(s.id)
        }
        mNotificationManager.createNotificationChannel(nNotifyChannel)
        mNotificationManager.notify(NotifyId, mbuilder.build())

    }
}