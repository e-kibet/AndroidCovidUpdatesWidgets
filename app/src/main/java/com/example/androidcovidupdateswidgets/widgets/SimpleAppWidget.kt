package com.example.androidcovidupdateswidgets.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.example.androidcovidupdateswidgets.R
import android.annotation.SuppressLint

import android.app.PendingIntent

import android.content.Intent
import android.net.Uri

import android.widget.RemoteViews
import com.example.androidcovidupdateswidgets.MainActivity


class SimpleAppWidget : AppWidgetProvider() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId->
            val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
                .let {
                    PendingIntent.getActivity(context, 0 , it, 0)
                }
            val views: RemoteViews = RemoteViews(
                context?.packageName,
                R.layout.simple_app_widget
            ).apply {
                setOnClickPendingIntent(R.id.tvWidget, pendingIntent)
            }

            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

}