package com.mynimef.sch.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.Carousel
import com.mynimef.sch.repository.Repository

class SecondFragmentViewModel: ViewModel() {

    val book = MutableLiveData<BookInfo>()

    private val similar = MutableLiveData<Array<Carousel>>()

    init {
        update()
    }

    fun getSimilar(): LiveData<Array<Carousel>> {
        return similar
    }

    fun update() {
        Repository.getSimilar(similar)
    }
}