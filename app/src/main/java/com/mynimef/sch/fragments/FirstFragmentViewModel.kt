package com.mynimef.sch.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mynimef.sch.repository.Repository
import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.ImagePreview

class FirstFragmentViewModel: ViewModel() {

    private val imagePreview = MutableLiveData<Array<ImagePreview>>()
    private val bestSellers = MutableLiveData<Array<BookInfo>>()

    init {
        update()
    }

    fun getCarousel(): LiveData<Array<ImagePreview>> {
        return imagePreview
    }

    fun getBestSellers(): LiveData<Array<BookInfo>> {
        return bestSellers
    }

    fun update() {
        Repository.getCarousel(imagePreview)
        Repository.getBestSellers(bestSellers)
    }
}