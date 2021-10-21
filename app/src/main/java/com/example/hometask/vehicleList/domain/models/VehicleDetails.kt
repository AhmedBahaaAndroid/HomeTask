package com.example.hometask.vehicleList.domain.models

import com.example.hometask.vehicleList.uiModel.VehicleUIModel

data class VehicleDetails(
    val id: Int,
    val make: String?,
    val model: String?,
    val price: Int?,
    val firstRegistration: String?,
    val mileage: String?,
    val fuel: String?,
    val images: List<String>?,
    val description: String?,
    val note: String?
)

fun VehicleDetails.toVehicleUIModel(): VehicleUIModel {
    return with(this) {
        VehicleUIModel(
            id = id,
            title = make + model,
            price = price,
            fuelType = fuel,
            photo = images,
            note = note
        )
    }
}