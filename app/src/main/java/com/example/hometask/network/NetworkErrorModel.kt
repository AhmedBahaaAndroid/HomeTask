package com.example.hometask.network

import com.google.gson.annotations.SerializedName

data class NetworkErrorModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String?
)
