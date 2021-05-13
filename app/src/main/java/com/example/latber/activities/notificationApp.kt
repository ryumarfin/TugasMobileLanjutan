package com.example.latber.activities

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class notificationApp : Application(){

    companion object{
        const val CHANNEL_1 = "channel1"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1 = NotificationChannel(
                    CHANNEL_1,
                    "channel satu",
                    NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = "Ini adalah channel saya"

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel1)
        }
    }
}