package com.nikhil.cryptocurrency.commons

sealed class Resources<T>(data: T? = null, message: String? = null) {
    class Success<T>(data: T?) : Resources<T>(data)
    class Error<T>(data: T?, message: String?) : Resources<T>(data, message)
    class Loading<T>(data: T?) : Resources<T>(data)
}