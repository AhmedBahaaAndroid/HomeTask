package com.example.hometask.vehicleList.uiModel

data class VehicleUIModel(
    val id: Int ,
    val photo: List<String>?,
    val title : String?,
    val price: Int?,
    val fuelType: String?,
    val note : String?
)