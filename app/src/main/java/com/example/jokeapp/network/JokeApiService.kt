package com.example.jokeapp.network

import com.example.jokeapp.model.JokeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface JokeApiService {
    @GET("joke/Any")
    fun getJoke(): Single<JokeResponse>
}