package com.example.hometask.network

class NetworkErrorMapper() {

    fun mapThrowable(throwable: Throwable): Exception {
        return Exception(throwable)
    }
}
