package com.klaudiak.gamescollector.utils


sealed class DataState<out T>(
) {
    data class Success<out T>(val data: T) : DataState<T>()
    object Loading: DataState<Nothing>()
    data class Error<T>(val exception: String) : DataState<Nothing>()
}