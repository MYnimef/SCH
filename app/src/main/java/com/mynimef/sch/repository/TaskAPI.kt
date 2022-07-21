package com.mynimef.sch.repository

import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.ImagePreview
import retrofit2.Call
import retrofit2.http.GET

interface TaskAPI {
    @GET("/stellardiver/ebookdata/carousel")
    fun getCarousel(): Call<Array<ImagePreview>>

    @GET("/stellardiver/ebookdata/best")
    fun getBestSellers(): Call<Array<BookInfo>>

    @GET("/stellardiver/ebookdata/similar")
    fun getSimilar(): Call<Array<ImagePreview>>
}