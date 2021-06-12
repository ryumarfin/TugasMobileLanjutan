
package com.example.latber.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.example.latber.R
import com.example.latber.activities.beli
import com.example.latber.activities.done
import com.example.latber.activities.menu

/**
 * Implementation of App Widget functionality.
 */
class CollectionWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    //Membuat remote views
    val remoteViews = RemoteViews(context.packageName, R.layout.collection_widget)

    //Membuat sebuah intent untuk melakukan aksi ke website
    val intent = Intent(Intent.ACTION_VIEW)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.data = Uri.parse("https://blackscreen.com")

    //membuat pending intent pada view
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
    remoteViews.setOnClickPendingIntent(R.id.gotoweb, pendingIntent)

    //untuk melakukan perubahan/update pada widget
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}