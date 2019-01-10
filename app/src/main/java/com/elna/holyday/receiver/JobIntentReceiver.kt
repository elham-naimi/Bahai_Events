package com.elna.holyday.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.elna.holyday.services.UpdateService


class JobIntentReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "JobIntentReceiver onReceive called")
        UpdateService.enqueueWork(context, intent)
    }

    companion object {
        private val TAG = JobIntentReceiver::class.java.simpleName
    }
}
