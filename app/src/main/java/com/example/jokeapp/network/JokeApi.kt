package com.example.jokeapp

import com.example.jokeapp.network.JokeApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object JokeApi {

    private val baseURL = "https://v2.jokeapi.dev/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val retrofitService: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }
}