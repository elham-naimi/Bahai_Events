package com.elna.presenter

import android.content.Context
import android.util.Log
import com.elna.datetime.DateTime
import com.elna.db.HolyDayRepository
import com.elna.db.HolyDaysDatabase
import com.elna.db.HolyDaysForGivenYear
import com.elna.model.HolyDay
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import kotlinx.coroutines.CoroutineScope
import org.json.JSONArray
import org.json.JSONObject
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit

object Presenter : IPresenter {

    private lateinit var repository: HolyDayRepository
    init {
        println("init complete")
    }

    fun parseHolyDays(data: ArrayList<HolyDaysForGivenYear>): ArrayList<HolyDay> {

        var holyDays = ArrayList<HolyDay>()
        for (i in 0 until data.size) {
            var jsonObject = JSONArray(data.get(i).holyDays)
            for (i in 0 until jsonObject.length()) {
                val formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss")
                val date: LocalDateTime = LocalDateTime.parse(((jsonObject.get(i) as JSONObject).get("holyDayWhen") as CharSequence?), formatter)
                holyDays.add(HolyDay((jsonObject.get(i) as JSONObject).get("holyDayName") as String, date))
            }
        }

        return holyDays;
    }

    override fun getUpcomingHolyDayIndex(list : ArrayList<HolyDay>) : Int {
        val currentDate = DateTime.getInstance().getCurrentDateTime()
        var index = 0
        for (holyDay in list) {
            if (currentDate.isBefore(holyDay.holyDayWhen)) {
                break
            }
            index++
        }
        return index
    }

    override fun getUpcomingHolyDays(): ArrayList<HolyDay> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDaysLeft(holyDay: LocalDateTime?) : Long {
        if (holyDay == null) return -1
        val today = LocalDateTime.now()
        var days = ChronoUnit.DAYS.between(today, holyDay)
        val minutes = ChronoUnit.MINUTES.between(today.plusDays(days), holyDay)
        if (minutes > 1)
            days++
        if (days < 0) days = 0
        return days
    }

     fun queryUpcomingHolydays(app : Context, scope: CoroutineScope): Flowable<ArrayList<HolyDay>> {
         var resultList = ArrayList<HolyDay>()

         return queryCurrentAndNextYearHolydays(app,scope).map { data -> data }
        /* return queryCurrentAndNextYearHolydays(app, scope).map { list ->
             var index: Int = getUpcomingHolyDayIndex(list)
             Log.i("TAG", "index+11" + (list[index].holyDayName))
             for (i in index until index + 11) {
                 list[i]?.let { resultList.add(list[i]) }
             }
             resultList

         }*/
     }


     fun queryCurrentAndNextYearHolydays(app: Context, scope: CoroutineScope): Flowable<ArrayList<HolyDay>> {
             val wordsDao = HolyDaysDatabase.getDatabase(app, scope).holyDayDao()

             repository = HolyDayRepository(wordsDao)
             return Flowable.zip(repository.queryCurrentYear(), repository.queryNextYear(), BiFunction<HolyDaysForGivenYear, HolyDaysForGivenYear, ArrayList<HolyDay>> { data1, data2 ->

                 var list = ArrayList<HolyDaysForGivenYear>()
                 list.add(data1)
                 list.add(data2)
                 Presenter.parseHolyDays(list)
             })


     }


}