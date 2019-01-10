package com.elna.holyday.util

import org.threeten.bp.LocalDateTime
import java.util.*

/* Making calls to LocalDataTime library through interface.Makes it loosely coupled. Useful to unittest using mockito.*/
class DateTime : IDateTime {
    override fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    override fun getNextYear(): Int {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 1)
        return calendar.get(Calendar.YEAR);
    }

    companion object {
        @Volatile
        private var INSTANCE : DateTime? = null

        fun getInstance(): DateTime {
            INSTANCE?.let { return it }

           synchronized(this){
              val instance = DateTime()
               INSTANCE = instance
               return instance
           }
        }
    }
    override fun getCurrentDateTime() : LocalDateTime {
       return LocalDateTime.now()
    }




}