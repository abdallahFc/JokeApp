package com.example.jokeapp

import android.annotation.SuppressLint
import com.example.jokeapp.model.JokeResponse
import io.reactivex.rxjava3.core.Single

class JokeRepository {
    @SuppressLint("CheckResult")
    fun getRandomJoke(): Single<State<JokeResponse>> {
        return JokeApi.retrofitService.getJoke().map<State<JokeResponse>> { jokeResponse ->
            State.Success(jokeResponse)
        }.onErrorReturn { throwable ->
            State.Error(throwable.message ?: "Unknown error occurred")
        }.doOnSubscribe { State.Loading }
    }
}


