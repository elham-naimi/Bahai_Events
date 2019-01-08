package com.elna.holyday.model

import org.threeten.bp.LocalDateTime


data class Feast(
        val name: String,
        val date: String
){
    constructor():this("",""){

    }
}
