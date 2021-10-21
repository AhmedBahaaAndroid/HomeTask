package com.example.hometask.vehicleList.api.models

import com.google.gson.annotations.SerializedName

data class RemoteVehicle(
    @SerializedName("id")
    val id: Int,
    @SerializedName("make")
    val make: String? = null,
    @SerializedName("model")
    val model: String? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("firstRegistration")
    val firstRegistration: String? = null,
    @SerializedName("mileage")
    val mileage: String? = null,
    @SerializedName("fuel")
    val fuel: String? = null,
    @SerializedName("images")
    val images: List<Image>? = null,
    @SerializedName("description")
    val description: String? = null
)

data class Image(@SerializedName("url") val imageUrl: String)