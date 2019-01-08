package com.elna.holyday.model

data class Year(
    val feasts: List<Feast>,
    val holyDays: List<HolyDayX>,
    val year: Int
)