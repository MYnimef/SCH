package com.mynimef.sch.repository

import android.os.Handler
import android.os.Message
import com.mynimef.sch.models.BookInfo
import com.mynimef.sch.models.Carousel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Network: NetworkAPI {

    private val task: TaskAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.task = retrofit.create(TaskAPI::class.java)
    }

    override fun getCarousel(handler: Handler) {
        val message = Message()
        task.getCarousel().enqueue(object: Callback<Array<Carousel>> {
            override fun onResponse(
                call: Call<Array<Carousel>>,
                response: Response<Array<Carousel>>
            ) {
                val callback = response.body()

                callback?.let {
                    message.arg1 = 0
                    message.obj = callback
                } ?: run {
                    message.arg1 = 1
                }

                handler.sendMessage(message)
            }

            override fun onFailure(call: Call<Array<Carousel>>, t: Throwable) {
                message.arg1 = -1
                handler.sendMessage(message)
            }
        })
    }

    override fun getBestSellers(handler: Handler) {
        val message = Message()
        task.getBestSellers().enqueue(object: Callback<Array<BookInfo>> {
            override fun onResponse(
                call: Call<Array<BookInfo>>,
                response: Response<Array<BookInfo>>
            ) {
                val callback = response.body()

                callback?.let {
                    message.arg1 = 0
                    message.obj = callback
                } ?: run {
                    message.arg1 = 1
                }

                handler.sendMessage(message)
            }

            override fun onFailure(call: Call<Array<BookInfo>>, t: Throwable) {
                message.arg1 = -1
                handler.sendMessage(message)
            }
        })
    }
}