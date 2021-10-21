package com.example.hometask

import com.example.hometask.vehicleList.api.models.Image
import com.example.hometask.vehicleList.api.models.RemoteVehicle
import com.example.hometask.vehicleList.api.models.RemoteVehiclesNote
import com.example.hometask.vehicleList.domain.models.VehicleDetails

val falkeVehiclesList = listOf(
    RemoteVehicle(
        id = 1,
        make = "fake maker",
        model = "fake model",
        price = 1000,
        firstRegistration = "fake reg",
        mileage = "fake mileage",
        fuel = "fake fuel",
        images = listOf(Image("fake url 1"), Image("fake url 2")),
        description = "fake description"
    ),
    RemoteVehicle(
        id = 1,
        make = "fake maker",
        model = "fake model",
        price = 1000,
        firstRegistration = "fake reg",
        mileage = "fake mileage",
        fuel = "fake fuel",
        description = "fake description"
    )
)

val fakeVehicleNote = listOf(
    RemoteVehiclesNote(
        vehicleId = 1,
        note = "note"
    )
)

val fakeVehicleDetails = listOf(
    VehicleDetails(
        id = falkeVehiclesList[0].id,
        make = falkeVehiclesList[0].make,
        model = falkeVehiclesList[0].model,
        price = falkeVehiclesList[0].price,
        firstRegistration = falkeVehiclesList[0].firstRegistration,
        mileage = falkeVehiclesList[0].mileage,
        fuel = falkeVehiclesList[0].fuel,
        description = falkeVehiclesList[0].description,
        note = fakeVehicleNote[0].note,
        images = falkeVehiclesList[0].images?.map { it.imageUrl }
    )
)