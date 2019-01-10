package com.elna.holyday.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.elna.holyday.Constants
import com.elna.holyday.receiver.JobIntentReceiver
import java.util.*

object AlarmScheduler {

    fun scheduleUpdate(context : Context) {
        val upcomingUpdateTime : Calendar =  getNextUpdateTime()
        val am : AlarmManager  =  context.getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager;
        val pi : PendingIntent = getAlarmIntent(context);
        am.cancel(pi);
        am.setRepeating(AlarmManager.RTC,  upcomingUpdateTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);

    }

    fun getNextUpdateTime(): Calendar {
        val nextUpdateTime = Calendar.getInstance()
        nextUpdateTime.set(Calendar.HOUR_OF_DAY, Constants.NEXT_UPDATE_HOUR)
        nextUpdateTime.set(Calendar.MINUTE, Constants.NEXT_UPDATE_MINUTE)
        nextUpdateTime.set(Calendar.SECOND, Constants.NEXT_UPDATE_SECOND)
        if (Calendar.getInstance().after(nextUpdateTime)) {
            nextUpdateTime.add(Calendar.DATE, 1)
        }
        return nextUpdateTime

    }

    private fun getAlarmIntent(context: Context): PendingIntent {
        val alarmIntent = Intent(context, JobIntentReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

    }

}