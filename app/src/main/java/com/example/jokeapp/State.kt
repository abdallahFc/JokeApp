package com.example.jokeapp

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error(val msq: String) : State<Nothing>()

    fun toData(): T? {
        return when (this) {
            is Success -> data
            else -> null
        }
    }
}
