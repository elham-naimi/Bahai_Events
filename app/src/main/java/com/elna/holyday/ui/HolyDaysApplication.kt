package com.elna.holyday.ui

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/* AndroidThreeTen is the library for datetime operations.
 * It needs to be initialised at beginning of the app */

class HolyDaysApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}

