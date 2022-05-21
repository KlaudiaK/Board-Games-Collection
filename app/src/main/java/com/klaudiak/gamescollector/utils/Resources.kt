package com.klaudiak.gamescollector.utils

import java.lang.Exception


sealed class Resources<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resources<T>(data)
    class Loading<T>(data: T? = null) : Resources<T>(data)
    class Error<T>(message: String, data: T? = null) : Resources<T>(data, message)
}



sealed class DataState<out T>(
) {
    data class Success<out T>(val data: T) : DataState<T>()
    object Loading: DataState<Nothing>()
    data class Error<T>(val exception: Exception) : DataState<Nothing>()
}