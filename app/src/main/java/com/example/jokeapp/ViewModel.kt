package com.example.jokeapp

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokeapp.model.JokeResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val jokeRepositry = JokeRepository()

    private val _joke = MutableLiveData<State<JokeResponse>>()
    val joke: MutableLiveData<State<JokeResponse>>
        get() = _joke

    init {
        getRandomJoke()
    }


    @SuppressLint("CheckResult")
    fun getRandomJoke() {
        disposable.add(jokeRepositry.getRandomJoke()
            .applySchedulers()
            .subscribe { jokeResponse ->
                _joke.postValue(jokeResponse)
            }
        )
    }


    fun <T : Any> Single<T>.applySchedulers(): Single<T> {
        return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}