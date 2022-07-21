package com.mynimef.sch.repository

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.Carousel


object Repository {

    private val network: NetworkAPI = Network()

    fun getCarousel(data: MutableLiveData<Array<Carousel>>) {
        val handler = Handler(Looper.getMainLooper()) {
            val res = it.arg1
            if (res == 0) {
                data.postValue(it.obj as Array<Carousel>)
            } else if (res == 1) {

            } else {
            }
            true
        }
        network.getCarousel(handler)
    }

    fun getBestSellers(data: MutableLiveData<Array<BookInfo>>) {
        val handler = Handler(Looper.getMainLooper()) {
            val res = it.arg1
            if (res == 0) {
                data.postValue(it.obj as Array<BookInfo>)
            } else if (res == 1) {

            } else {
            }
            true
        }
        network.getBestSellers(handler)
    }

    fun getSimilar(data: MutableLiveData<Array<Carousel>>) {
        val handler = Handler(Looper.getMainLooper()) {
            val res = it.arg1
            if (res == 0) {
                data.postValue(it.obj as Array<Carousel>)
            } else if (res == 1) {

            } else {
            }
            true
        }
        network.getSimilar(handler)
    }
}