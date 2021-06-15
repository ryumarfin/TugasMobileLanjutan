package com.example.latber.widgets
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.*
import android.util.Log
import android.widget.RemoteViews
import com.example.latber.R
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class QoutesWidget : AppWidgetProvider() {
    private val client = OkHttpClient()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateQuotesAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
    //kami tidak menghandle ketika widget di buat pertama kali
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }
    //kami tidak menhandle ketika widget di hapus/remove
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    //fungsi ini mmenangkap broadcast
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        //setelah menerima broadcast panggil appwidgetmanger untuk
        // mengetahui widget id yang sedang berjalan
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context!!.packageName, javaClass.name))
        //lakukan perulangan untuk mengupdate seluruh widget yang terdaftar
        for(appWidgetId in appWidgetIds){
            //Panggil perintah untuk update widget
            updateQuotesAppWidget(context, appWidgetManager, appWidgetId)
        }

    }

    //fungsi untuk melakukan request untuk mengambil data quotes
    private fun run(url: String, views : RemoteViews, context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        //kami akan menggunakan libary okhttp utk melakukan request url
        //membuat request
        val request = Request.Builder().url(url).build()
        //mengambiil response secara async
        client.newCall(request).enqueue(object : Callback {
            //ketika request gagal
            override fun onFailure(call: Call, e: IOException) {
                Log.i("FAILREQ", "fail$e")
            }
            //ketika response http berhasil dikembaliikan oleh server
            override fun onResponse(call: Call, response: Response) {
                //membaca isi response
                response.body?.string()?.let {
                    Log.i("SUCCREQ", it)
                    //Membuat dan menyimpan quotes kedalam JSONArray.
                    var jsonArray = JSONArray(it)
                    //untuk mendapatkan angka random index
                    var index = (0 until jsonArray.length()).random()
                    //mengambil data pada index random
                    var objectData = jsonArray.getJSONObject(index)
                    val text = objectData.getString("text")
                    val author = objectData.getString("author")
                    //menampilkan hasil kedalam textview
                    views.setTextViewText(R.id.qoutes_text_Widget,text)
                    views.setTextViewText(R.id.qoutes_author_Widget, "- $author")
                    //panggil untuk mengupdate tampilan widget
                    appWidgetManager.updateAppWidget(appWidgetId, views)
                }
                response.close()
            }
        })
    }
    //fungsi untuk mengupdate widget
    private fun updateQuotesAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.qoutes_widget)
        //panggil fungsi run untuk melakukan request untuk mengambil data quotes
        run("https://type.fit/api/quotes", views,context , appWidgetManager, appWidgetId)
        //panggil untuk mengupdate tampilan widget

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

