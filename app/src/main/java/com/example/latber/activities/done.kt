package com.example.latber.activities

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.latber.*
import kotlinx.android.synthetic.main.activity_done.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime


class done : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done)

        saveRincian.setOnClickListener{
            val currentDateTime = LocalDateTime.now()
            val sdf = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME)
            if(isExternalStorageReadable()){
                writeFileExternal()
                Toast.makeText(this, "${sdf}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun writeFileExternal() {
        val currentDateTime = LocalDateTime.now()
        val sdf = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME)
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        if(!myDir.exists()){
            myDir.mkdir()
        }
        File(myDir,"${sdf}.txt") .apply {
            writeText(rincian.text.toString())
        }
    }
/*    private fun readFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        var readFile = ""
        File(myDir,edit_text3.text.toString()).forEachLine(Charsets.UTF_8) {
            readFile += it
        }
        edit_text1.setText(readFile)
    }*/

    fun isExternalStorageReadable(): Boolean{
        if(ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    123)
        }
        var state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            123 -> {
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    Toast.makeText(this, "Izin Diberikan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun toSparepartPage(view: View) {
        finish()
    }
}


