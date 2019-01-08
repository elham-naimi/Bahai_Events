package com.elna.db

import io.reactivex.Flowable
import org.json.JSONArray
import org.json.JSONObject
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import android.util.Log
import com.elna.model.HolyDay
import com.elna.util.getCurrentYear
import com.elna.util.getNextYear
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class HolyDayRepository(private val holDayDao: HolyDaysForGivenYearDao) {

    fun insert(holyDays: HolyDaysForGivenYear) {
        holDayDao.insert(holyDays)
    }


    fun queryCurrentYear(): Flowable<HolyDaysForGivenYear>? {
        return holDayDao.getHolyDaysForYear(getCurrentYear()).subscribeOn(Schedulers.io())
    }

    fun queryNextYear(): Flowable<HolyDaysForGivenYear>? {
        return holDayDao.getHolyDaysForYear(getNextYear()).subscribeOn(Schedulers.io())
    }




}



