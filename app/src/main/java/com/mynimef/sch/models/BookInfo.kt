package com.mynimef.sch.models

data class BookInfo(
    val id: Int,
    val title: String,
    val author: String,
    val price: Double,
    val image: String,
    val rate: RateInfo
)
