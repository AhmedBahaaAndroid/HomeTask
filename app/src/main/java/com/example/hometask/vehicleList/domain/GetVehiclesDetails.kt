package com.example.hometask.vehicleList.domain

import com.example.hometask.vehicleList.domain.models.VehicleDetails
import com.example.hometask.vehicleList.repo.VehiclesRepository
import io.reactivex.rxjava3.core.Single

class GetVehiclesDetails(private val vehiclesRepository: VehiclesRepository) {

    operator fun invoke(): Single<List<VehicleDetails>> {
        return Single.zip(
            vehiclesRepository.getVehicles(),
            vehiclesRepository.getVehiclesNotes(),
            { vehicles, notes ->
                vehicles.map { vehicle ->
                    VehicleDetails(
                        id = vehicle.id,
                        make = vehicle.make,
                        model = vehicle.model,
                        price = vehicle.price,
                        firstRegistration = vehicle.firstRegistration,
                        mileage = vehicle.mileage,
                        fuel = vehicle.fuel,
                        images = vehicle.images?.map { it.imageUrl },
                        description = vehicle.description,
                        note = notes.firstOrNull { vehicle.id == it.vehicleId }?.note
                    )
                }
            })
    }
}