package com.madeean.day14.data.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val MainServer = "https://api.themoviedb.org/3/"
    const val ImageServer = "https://image.tmdb.org/t/p/w500/"

    private val retrofitClient: Retrofit.Builder by lazy {

        Retrofit.Builder()
            .baseUrl(MainServer)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}