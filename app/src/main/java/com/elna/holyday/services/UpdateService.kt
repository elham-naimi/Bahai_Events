package com.elna.holyday.services

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.util.Log
import android.widget.RemoteViews


class UpdateService : JobIntentService(), IService {

    override fun onHandleWork(intent: Intent) {
        run {
            sendNotification()
            if(widgetExists())
                updateWidget()
        }

    }



    override fun widgetExists() : Boolean {
        return true
    }

    override fun updateWidget() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendNotification() {
    }

    companion object {
        private val TAG = UpdateService::class.java.simpleName
        private var context: Context? = null
        /* Give the Job a Unique Id */
        private val JOB_ID = 1000

        fun enqueueWork(context: Context, intent: Intent) {
            Log.i(TAG, "Service called enque work")
            UpdateService.context = context
            JobIntentService.enqueueWork(context, UpdateService::class.java, JOB_ID, intent)
        }
    }


}
