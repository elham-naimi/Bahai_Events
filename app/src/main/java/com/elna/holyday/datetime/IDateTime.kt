package com.elna.holyday.datetime

import org.threeten.bp.LocalDateTime

interface IDateTime {
  fun getCurrentDateTime() : LocalDateTime
}
