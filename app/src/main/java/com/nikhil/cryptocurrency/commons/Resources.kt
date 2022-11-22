package com.nikhil.cryptocurrency.commons

sealed class Resources<T>(data: T? = null, message: String? = null) {
    class Success<T>(data: T?) : Resources<T>(data)
    class Error<T>( message: String?) : Resources<T>(message=message)
    class Loading<T>() : Resources<T>()
}