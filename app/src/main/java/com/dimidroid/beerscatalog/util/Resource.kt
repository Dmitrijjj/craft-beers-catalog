package com.dimidroid.beerscatalog.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>() : Resource<T>()
    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)
}