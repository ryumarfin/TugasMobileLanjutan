package com.example.latber

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.latber.activities.menu
import com.example.latber.fragments.imageView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

//2. pembuatan extra status
private const val EXTRA_STATUS = "ExtraStatus"

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        val imgView = findViewById<ImageView>(R.id.imgV)
        val tambahFoto = findViewById<TextView>(R.id.tambahFoto)

        var strURL = "http://blogs.quovantis.com/wp-content/uploads/2017/05/Kotlin-A_Post.png"

        val handler= object : Handler(Looper.getMainLooper()){
            override fun handleMessage(inputMessage: Message) {
                val photoTask = inputMessage.obj as Bitmap
                imgView.setImageBitmap(photoTask)
            }

        }
        tambahFoto.setOnClickListener{
            Thread(Runnable {
                doAsync {
                    var myPhoto = processBitMap(strURL)
                    uiThread {
                        imgView.setImageBitmap(myPhoto)
                    }


                }


                //val bitmap = processBitMap(strURL)

                /*val msg = Message.obtain(handler)
                msg.obj = bitmap
                msg.sendToTarget()*/

               /*imgView.post{
                    println("menambahkan foto profil")
                    imgView.setImageBitmap(bitmap)
                }*/



            }).start()


        }

    }

    private fun processBitMap(url : String): Bitmap? {
        return try{
            val url = URL(url)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)

            myBitmap
        }catch (e: IOException){
            e.printStackTrace()
            null
        }

    }

    fun RegistertoSparepartPage1(view: View) {
        var intentSparePart = Intent(this, menu::class.java)
        startActivity(intentSparePart)
        finish()
    }

 /*   fun cek(view: View) {

        statusMail.setText( " " + et_Email_Register.text + "   Belum terdaftar")
    }

    //1. Pembuatan save instance state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATUS, statusMail.text.toString())
    }

    //3. pembacaan saveinstance state
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        statusMail.text =   savedInstanceState?.getString(EXTRA_STATUS) ?: "-"
    }*/
}