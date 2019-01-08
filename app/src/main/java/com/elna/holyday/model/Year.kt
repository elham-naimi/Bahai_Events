package com.elna.holyday.model

data class Year(
        val feasts: ArrayList<Feast>,
        val holyDays: List<HolyDay>,
        val year: Int
){
  //  constructor():this(emptyList(), emptyList(),2018)
  constructor():this( ArrayList(),ArrayList(),2018)
}