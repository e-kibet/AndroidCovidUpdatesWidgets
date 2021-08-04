package com.example.androidcovidupdateswidgets

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPinAppWidget()
    }

    private fun requestPinAppWidget(){
        val context: Context = applicationContext()
        val appWidgetManager: AppWidgetManager = context.getSystemService(AppWidgetManager::class.java)
        val myProvider = ComponentName(context, MyAppWidgetProvider::class.java)

        val successCallback: PendingIntent? = if (appWidgetManager.isRequestPinAppWidgetSupported) {
            // Create the PendingIntent object only if your app needs to be notified
            // that the user allowed the widget to be pinned. Note that, if the pinning
            // operation fails, your app isn't notified.
            Intent(...).let { intent ->
                // Configure the intent so that your app's broadcast receiver gets
                // the callback successfully. This callback receives the ID of the
                // newly-pinned widget (EXTRA_APPWIDGET_ID).
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        } else {
            null
        }

        successCallback?.also { pendingIntent ->
            appWidgetManager.requestPinAppWidget(myProvider, null, pendingIntent)
        }
    }
}