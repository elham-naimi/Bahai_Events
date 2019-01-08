package com.elna.presenter

import com.elna.model.HolyDay
import org.threeten.bp.LocalDateTime

interface IPresenter {
    fun getUpcomingHolyDayIndex(list : ArrayList<HolyDay>) : Int
    fun getUpcomingHolyDays(): ArrayList<HolyDay>
    fun getDaysLeft(holyDay: LocalDateTime?): Long
}