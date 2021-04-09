package com.example.latber

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
    //Membuat channel khusus untuk notifikasi ini
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        createNotificationChannel()



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

    //Membuat notifikasi channel agar notifikasi dapat muncul
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            //Deklarasi notifikasi manager
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    //fungsi untuk memunculkan atau mengirim notifikasi
    private fun sendNotification(){
        //buat intent agar ketika notifikasi di klik maka beralih ke menu activity
        val intent:Intent = Intent(this,menu::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //membuat pending intent
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this
                ,0,intent,0)

        //menkonversi gambar menjadi bitmap agar dapat dimunculkan ke notifikasi
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.montirface)
        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.logo)



        //mengambil nama pendaftar agar dapat muncul di notifikasi
        val nama = findViewById<EditText>(R.id.et_firstname_Register).text

        //membangun sebuah notifikasi dan mengatur isi didalamnya
        // seperti judul, isi notifikasi dan lainnya.
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //menampilkan judul notifikasi
                .setContentTitle("MarketPlace Registration")
                //menampilkan detail text notifikasi
                .setContentText("Terima kasih $nama telah mendaftar ")
                //menambahkan icon
                .setLargeIcon(bitmapLargeIcon)
                //menambahkan gambar ke dalam notifikasi
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
              //  memanggil pending intent agar dapat melakukan aksi ketika di klik
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
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
        //Panggil fungsi untuk memunculkan notifikasi di salah satu button yang
        //telah anda tentukan
        sendNotification()

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