package com.elna.holyday.presenter

import com.elna.holyday.model.Event
import com.elna.holyday.model.HolyDay
import com.elna.holyday.model.Years
import org.threeten.bp.LocalDateTime

interface IPresenter {
    fun getUpcomingHolyDayIndex(list : ArrayList<HolyDay>) : Int
    fun getUpcomingHolyDays(): ArrayList<HolyDay>
    fun getDaysLeft(holyDay: LocalDateTime?): Long
    fun filter(results: Years) : Years
    fun getUpcomingEvents(years : Years) : ArrayList<Event>
}