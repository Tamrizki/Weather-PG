package com.example.weather_pgtest.utils

sealed class State<T> {
    data class Loading<T>(val isLoading: Boolean = true) : State<T>()
    data class Success<T>(val data : T) : State<T>()
    data class Error<T>(val throwable: Throwable) : State<T>()
}
