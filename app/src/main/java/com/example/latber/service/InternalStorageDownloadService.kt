package com.example.latber.service

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.latber.DOWNLOAD_TO_INTERNAL_JOB_ID
import com.example.latber.FAVORITE_IMAGE_URL
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.WeakReference

//disini ada changes
class InternalStorageDownloadService : JobIntentService() {

    private var mContext: WeakReference<Context> = WeakReference(this)

    val mHandler: Handler = Handler(Looper.getMainLooper())
    fun showToast(text : String=""){
        mHandler.post(Runnable {
            Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onHandleWork(intent: Intent) {
        showToast("Saving Image To Favorite!")
        val url = intent.getStringExtra(FAVORITE_IMAGE_URL)

        mContext.get()?.let {

            val bitmap = Glide.with(it).asBitmap().load(url).submit().get()

            try {
                var file = File(it.filesDir, "Photos")
                if (!file.exists()) {
                    file.mkdir()

                }
                file = File(file, "${java.util.UUID.randomUUID()}.jpg")
                val outStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)

                outStream.flush()
                outStream.close()

            } catch (e: Exception) {
                showToast("Error Saving image to Favorite! ${e}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("Saving Image To Favorite Done!")

    }

    companion object{
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, InternalStorageDownloadService::class.java, DOWNLOAD_TO_INTERNAL_JOB_ID,intent)
        }
    }


}