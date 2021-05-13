package com.example.latber

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class airPlaneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //Jika mode pesawat aktif akan diberitahukan melalui toast
        while(Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON,0) ==0){
            Toast.makeText(context, "Mohon nonaktifkan mode pesawat", Toast.LENGTH_LONG).show()
            }
        }
}