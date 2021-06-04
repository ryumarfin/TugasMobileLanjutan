package com.example.latber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latber.data.Notes
import com.example.latber.sql.DBHelper
import kotlinx.android.synthetic.main.activity_preload.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreloadActivity : AppCompatActivity() {
    //akses database
    var mydbHelper : DBHelper?= null

    //contoh data
    private var catatan = listOf(
            Notes(1,"Senin","beli handuk"),
            Notes(2,"Selasa","beli jam"),
            Notes(3,"Rabu","beli jam"),
            Notes(4,"Kamis","beli jam"),
            Notes(5,"Jumat","beli jam"),
            Notes(6,"Sabtu","beli jam"),
            Notes(7,"Minggu","beli jam"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preload)
        //apabila ya maka lakukan proses load data
        btnya.setOnClickListener{
            executeLoadDataTransaction()
        }


        //apabila tidak maka finish
        btntidak.setOnClickListener{
            finishThisActivity()
        }
    }

    private fun executeLoadDataTransaction() {
        btntidak.isEnabled = false
        btnya.isEnabled = false
        myProgress.progress = 0
        myProgress.max =  catatan.size
        mydbHelper = DBHelper(this)
        doAsync {
            //lakukan begintransaction saat proses transaction
            mydbHelper?.beginNoteTransaction()
            for (notedata in catatan ){
                //tambahkan sesuai dengan data tadi ke dalam catatan
                mydbHelper?.tambahNoteTransaction(notedata)
                //lakukan penambahan progressbar di ui thread
                uiThread {
                    myProgress.progress += 1
                }
            }
            //panggil suksesnotetransaction ketika sukses
            mydbHelper?.successNoteTransaction()
            //lalu panggil endnotetrasaction karena sudah berakhir
            mydbHelper?.endNoteTransaction()
            //
            uiThread {
                finishThisActivity()
            }
        }
    }

    private fun finishThisActivity() {
        //deklarasi firstrun
        var myFirstRunSharedPref = FirstRunSharedPref(this)
        //buat firstrun jadi false agar tidak berjalan
        myFirstRunSharedPref.firstRun = false
        //lalu finish
        this.finish()
    }

    private fun executeLoadData() {
        btntidak.isEnabled = false
        btnya.isEnabled = false
        myProgress.progress = 0
        myProgress.max =  catatan.size
        mydbHelper = DBHelper(this)
        doAsync {
            for(notesData in catatan){
                mydbHelper?.tambahNote(notesData)
                uiThread {
                    myProgress.progress += 1
                }
            }
            uiThread {
                finishThisActivity()
            }
        }
    }
}