package com.elna.datetime

import org.threeten.bp.LocalDateTime

interface IDateTime {
  fun getCurrentDateTime() : LocalDateTime
}
