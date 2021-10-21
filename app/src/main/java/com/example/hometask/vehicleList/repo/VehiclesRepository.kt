package com.example.hometask.vehicleList.repo

import com.example.hometask.vehicleList.api.VehiclesApi

class VehiclesRepository(private val vehiclesService: VehiclesApi) {
    fun getVehicles() = vehiclesService.getVehicles()
    fun getVehiclesNotes() = vehiclesService.getVehiclesNotes()
}
