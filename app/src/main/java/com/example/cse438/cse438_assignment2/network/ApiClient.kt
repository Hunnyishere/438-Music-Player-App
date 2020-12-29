package com.example.cse438.cse438_assignment2.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    const val BASE_URL = "https://api.deezer.com/"

    fun makeRetrofitService():SearchInterface{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(SearchInterface::class.java)
    }
}