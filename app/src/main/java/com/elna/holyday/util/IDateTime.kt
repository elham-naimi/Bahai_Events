package com.elna.holyday.util

import org.threeten.bp.LocalDateTime

interface IDateTime {
  fun getCurrentDateTime() : LocalDateTime
  fun getCurrentYear() : Int
  fun getNextYear() : Int
}
