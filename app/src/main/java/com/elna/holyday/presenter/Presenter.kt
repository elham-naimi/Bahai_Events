package com.elna.holyday.presenter

import android.util.Log
import com.elna.holyday.Constants
import com.elna.holyday.util.DateTime
import com.elna.holyday.model.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import kotlin.collections.ArrayList

object Presenter : IPresenter {


    @JvmField val TAG: String = Presenter::class.java.simpleName

    // TODO : Update to return only upcoming 5 events
    override fun getUpcomingEvents(years: Years): ArrayList<Event> {

        val events : ArrayList<Event> =  ArrayList()

        for(eventsForYear in years.years){
         val year : Int = eventsForYear.year
          for(feast in eventsForYear.feasts){
              Log.i(TAG,"feast -> "+feast)
              val formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss")
              val date : LocalDateTime = LocalDateTime.parse((feast.date as CharSequence?), formatter)
              events.add(Event(feast.name,date,Constants.EVENT_FEAST, getDaysLeft(date)))
          }
          for(holyday in eventsForYear.holyDays){
              val formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss")
              val date : LocalDateTime = LocalDateTime.parse((holyday.date as CharSequence?), formatter)
              events.add(Event(holyday.name,date,Constants.EVENT_HOLYDAY, getDaysLeft(date)))
          }
        }
        return events
    }

    override fun filter(allYears : Years): Years {
        var currUpcomingYears = Years()
        for( year in allYears.years){
            if(year.year == DateTime.getInstance().getCurrentYear() ||
                    year.year ==DateTime.getInstance().getNextYear() ){
                 currUpcomingYears.years.add(year)
            }
        }
        return currUpcomingYears
    }


    override fun getUpcomingHolyDayIndex(list : ArrayList<HolyDay>) : Int {
        val currentDate = DateTime.getInstance().getCurrentDateTime()
        var index = 0
        for (holyDay in list) {
           /* if (currentDate.isBefore(holyDay.holyDayWhen)) {
                break
            }*/
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

}