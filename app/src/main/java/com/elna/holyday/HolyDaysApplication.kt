package com.elna.kotlinfragment

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class HolyDaysApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}

