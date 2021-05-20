package com.example.latber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_done.*
import kotlinx.android.synthetic.main.activity_music_room.*

var myIntentMPService : Intent? = null
class MusicRoom : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_room)

        Musicplay.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            R.id.Musicplay -> {
                if(Musicplay.text.toString().toUpperCase().equals("PLAY")){
                    Musicplay.text = "STOP"
                    myIntentMPService?.setAction(ACTION_PLAY)
                    startService(myIntentMPService)
                }
                else{
                    Musicplay.text = "PLAY"
                    myIntentMPService?.setAction(ACTION_STOP)
                    startService(myIntentMPService)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(myIntentMPService==null){
            myIntentMPService = Intent(this, MyMPService::class.java)
            myIntentMPService?.setAction(ACTION_CREATE)
            startService(myIntentMPService)
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(myIntentMPService)
    }

    fun toSparepartPage(view: View) {
        finish()
    }

}