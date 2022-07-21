package com.mynimef.sch.repository

import android.os.Handler

interface NetworkAPI {

    fun getCarousel(handler: Handler)
    fun getBestSellers(handler: Handler)
}