package com.elna.holyday.model

data class HolyDaysDatabase(
        val years: List<Year>
){
    constructor():this(emptyList())
}