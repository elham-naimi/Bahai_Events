package com.elna.holyday.model

import org.threeten.bp.LocalDateTime


data class Event(
        val name: String,
        val date: LocalDateTime,
        val type: Int,
        val daysleft : Long

): Comparable<Event> {
    override fun compareTo(other: Event): Int {
         return date.compareTo(other.date)
    }
}

