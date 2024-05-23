package com.example.greatify.network.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retroinstance {
    private const val BASE_URL ="https://heycampusdev.com/api/student-app/V1.2/"
    private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val Instance : ApiInterface by lazy {
            retrofit.create(ApiInterface::class.java)
    }
}