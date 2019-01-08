package com.elna.holyday.model

import org.threeten.bp.LocalDateTime

data class HolyDay(
        val name: String,
        val date: String
){
    constructor():this("", "")
}