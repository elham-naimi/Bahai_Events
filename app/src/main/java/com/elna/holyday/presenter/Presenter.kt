package com.elna.holyday.presenter

import android.util.Log
import com.elna.holyday.Constants
import com.elna.holyday.FirebaseRepository
import com.elna.holyday.util.DateTime
import com.elna.holyday.model.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import kotlin.collections.ArrayList

object Presenter : IPresenter {

    @JvmField val TAG: String = Presenter::class.java.simpleName

   /* override fun getUpcomingEvents(): Observable<ArrayList<Event>> {
                Log.i("Flow","Presenter::getUpcomingEvents")
         return FirebaseRepository().getAllEvents().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .map { list -> filter(list) }
                .map { subList -> getEvents(subList) }
                .map { events ->
                      Log.i("Flow","Presenter.map")
                      sortEvents(events)
                }


    }*/

     fun sortEvents(events: ArrayList<Event>): List<Event> {
        Log.i("Flow","sortEvent")
        var result = ArrayList<Event>()
        for(event in events){
            Log.i("Flow","Presenter.for")
            if(event.date.isAfter(DateTime.getInstance().getCurrentDateTime()))
                result.add(event)
        }
         return result.sortedWith(compareBy({it.date}))
    }

     fun getEvents(subList: Years): ArrayList<Event> {
        Log.i("Flow","Presenter.getEvents")
        val events: ArrayList<Event> = ArrayList()
        for (eventsForYear in subList.years) {
            val year: Int = eventsForYear.year
            for (feast in eventsForYear.feasts) {
                Log.i(TAG, "feast -> " + feast)
                val formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss")
                val date: LocalDateTime = LocalDateTime.parse((feast.date as CharSequence?), formatter)
                events.add(Event(feast.name, date, Constants.EVENT_FEAST, getDaysLeft(date)))
            }
            for (holyday in eventsForYear.holyDays) {
                val formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss")
                val date: LocalDateTime = LocalDateTime.parse((holyday.date as CharSequence?), formatter)
                events.add(Event(holyday.name, date, Constants.EVENT_HOLYDAY, getDaysLeft(date)))
            }
        }
        return events
    }

    private fun filter(allYears : Years): Years {
        Log.i("Flow","Presenter.filter")
        var currUpcomingYears = Years()
        for( year in allYears.years){
            if(year.year == DateTime.getInstance().getCurrentYear() ||
                    year.year ==DateTime.getInstance().getNextYear() ){
                 currUpcomingYears.years.add(year)
            }
        }
        return currUpcomingYears
    }

    private fun getDaysLeft(holyDay: LocalDateTime?) : Long {
        if (holyDay == null) return -1
        val today = LocalDateTime.now()
        var days = ChronoUnit.DAYS.between(today, holyDay)
        val minutes = ChronoUnit.MINUTES.between(today.plusDays(days), holyDay)
        if (minutes > 1)
            days++
        if (days < 0) days = 0
        return days
    }

}