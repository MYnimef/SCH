package com.mynimef.sch.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.ImagePreview
import com.mynimef.sch.repository.Repository

class SecondFragmentViewModel: ViewModel() {

    val book = MutableLiveData<BookInfo>()

    private val similar = MutableLiveData<Array<ImagePreview>>()

    init {
        update()
    }

    fun getSimilar(): LiveData<Array<ImagePreview>> {
        return similar
    }

    fun update() {
        Repository.getSimilar(similar)
    }
}