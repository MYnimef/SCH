package com.mynimef.sch.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mynimef.sch.repository.Repository
import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.Carousel

class FirstFragmentViewModel: ViewModel() {

    private val carousel = MutableLiveData<Array<Carousel>>()
    private val bestSellers = MutableLiveData<Array<BookInfo>>()

    init {
        update()
    }

    fun getCarousel(): LiveData<Array<Carousel>> {
        return carousel
    }

    fun getBestSellers(): LiveData<Array<BookInfo>> {
        return bestSellers
    }

    fun update() {
        Repository.getCarousel(carousel)
        Repository.getBestSellers(bestSellers)
    }
}