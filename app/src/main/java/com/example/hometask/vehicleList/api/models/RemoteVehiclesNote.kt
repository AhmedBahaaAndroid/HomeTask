package com.example.hometask.vehicleList.api.models

import com.google.gson.annotations.SerializedName

data class RemoteVehiclesNote(
    @SerializedName("vehicleId") val vehicleId: Int,
    @SerializedName("note") val note: String
)