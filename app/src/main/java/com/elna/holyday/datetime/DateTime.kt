package com.elna.holyday.datetime

import org.threeten.bp.LocalDateTime
/* Making calls to LocalDataTime library through interface.Makes it loosely coupled. Useful to unittest using mockito.*/
class DateTime : IDateTime {

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