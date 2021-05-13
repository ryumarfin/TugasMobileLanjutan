package com.example.latber.jobScheduler

import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import com.example.latber.QUOTES_DATA
import com.example.latber.QUOTES_SIGNAL
import com.example.latber.data.Quotes
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.text.Charsets.UTF_8


//implementasi JobService pada Class lalu implementasikan membernya
class QuotesScheduler : JobService(){
    //menjalankan Scheduler
    override fun onStartJob(params: JobParameters?): Boolean {
        //panggil fungsi dengna melempar parameter
        GetQuotes(params)
        //menjalankan proses di thread yang berbeda
        return true
    }

    //menghentikan Job ketika kondisi tidak terpenuhi atau gagal
    override fun onStopJob(params: JobParameters?): Boolean {
        //lakukan reschedule
        return true
    }

    private fun GetQuotes(params: JobParameters?) {
        //definisikan clientnya
        val client = AsyncHttpClient()
        //url API
        val url = "https://type.fit/api/quotes"
        //untuk menampung data yang di request
        val charset = Charsets.UTF_8
        //definisikan handler untuk proses async yang akan menangani responde dari server
        var handler = object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                //mengkonversi response body ke string
                val result = responseBody?.toString(charset) ?: "Kosong"
                Log.i("RESULT",result)
                var jsonArray = JSONArray(result)
                //untuk mendapatkan angka random index
                var index = (0..jsonArray.length() - 1).random()
                //mengambil data pada index random
                var objectData = jsonArray.getJSONObject(index)
                //definisikan intent
                var intentQuotes = Intent(QUOTES_SIGNAL)
                //isi data yang ditangkap dari server ke intent untuk dikirimkan dengan parcelable
                intentQuotes.putExtra(QUOTES_DATA,Quotes(
                    objectData.getString("text"),
                    objectData.getString("author"),
                ))
                //mengirimkan intent yang berisi data secara asynchronous
                sendBroadcast(intentQuotes)
                //lakukan reschedule
                jobFinished(params,true)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.i("RESULT","Failed")
                //lakukan reschedule ulang
                jobFinished(params,true)
            }
        }
        //lakukan request pada url yang akan ditangani oleh handler
        client.get(url,handler)
    }
}