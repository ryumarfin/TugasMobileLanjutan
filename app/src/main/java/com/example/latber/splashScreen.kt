package com.example.latber

import android.content.Intent
import android.graphics.Typeface
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.latber.activities.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

//deklarasi variabel soundpool dan soundid
private var sp : SoundPool? = null
private var soundID = 0

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }



       // val typeface: Typeface = Typeface.createFromAsset(assets,"Rolling-bold.ttf")
        // tv_judul.typeface = typeface
    }

    override fun onStart() {
        super.onStart()
        //mendeteksi versi dari android
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //jika diatas lolipop maka gunakan cara baru
            createNewSoundPool()
        } else{
            //jika dibawah lolipop maka gunakan cara lama
            createOldSoundPool()
        }

        //memastikan bahwa soundpool telah di load
        sp?.setOnLoadCompleteListener{soundPool,id,status ->
            if(status != 0){
                Toast.makeText(this,"Load Gagal",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
            }

        }
        //meload sound dan memasukkan ke variabel soundID
        soundID = sp?.load(this, R.raw.welcome_female,1) ?: 0

    }

    //fungsi cara lama
    private fun createOldSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(15)
            .build()
    }

    //fungsi cara baru
    private fun createNewSoundPool() {
         sp = SoundPool(15, AudioManager.STREAM_MUSIC,0)
    }

    //siklus stop
    override fun onStop() {
        super.onStop()
        //menghilangkan sp agar memori ringan
        sp?.release()
        sp = null
    }

    //fungsi klik pada splash screen
    //dijalankan ketika layout splash screen di klik di sembarang tempat
    fun splashButton(view: View) {
        //ketika soundID tidak = 0 berarti (soundID berisi file)
        if(soundID!=0){
            //maka mainkan file tersebut dengan atur volume kanan kirinya, priority dan pengulangannya
            sp?.play(soundID,0.99f,.99f,1,0,.99f)
        }


        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                startActivity(Intent(this@splashScreen, MainActivity::class.java))
                finish()
            },
            1000
        )
    }
}