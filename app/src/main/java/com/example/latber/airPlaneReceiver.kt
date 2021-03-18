package com.example.latber

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class airPlaneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        //Jika mode pesawat mati dan jika mode pesawat hidup
        if(Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON,0) ==0){
            Toast.makeText(context, "Mode Pesawat : OFF", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context, "Mode Pesawat : ON", Toast.LENGTH_LONG).show()
        }
    }
}