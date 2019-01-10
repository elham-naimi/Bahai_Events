package com.elna.holyday.util

import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.elna.holyday.R
import com.elna.holyday.model.Event
import com.elna.holyday.model.HolyDay
import com.elna.holyday.ui.ItemListActivity



object NotificationManager {


    private val TAG = NotificationManager::class.java.simpleName
    fun sendNotification(context: Context, holyday: Event) {


        Log.i(TAG, "sendNotification")
        createNotificationChannel(context)

        val date = holyday.date.minusDays(1)
        val intent = Intent(context, ItemListActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notificationManager = NotificationManagerCompat.from(context.applicationContext)

        val isLollipop = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP
        var smallIcon = context.resources.getIdentifier("notification_small_icon", "drawable", context.packageName)
        if (smallIcon <= 0 || !isLollipop) {
            smallIcon = context.applicationInfo.icon
        }


        val message = "Begins evening of " + date.getDayOfWeek().toString() +
                ", " + date.getMonth().toString() + " " + date.getDayOfMonth()

        val builder = NotificationCompat.Builder(context, "1")

        builder.addAction(smallIcon, holyday.name, pendingIntent)
        builder.setContentTitle(holyday.name)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(smallIcon)
                .setAutoCancel(true)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setContentIntent(pendingIntent)

        notificationManager.notify(1, builder.build())
        Log.i(TAG, "createNotificationChannel check 3")


    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        Log.i(TAG, "createNotificationChannel check 1")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i(TAG, "createNotificationChannel check 2")

            val name = ""
            val description = ""
            val importance = android.app.NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = context.getSystemService(android.app.NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }
}
