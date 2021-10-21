package com.example.hometask.vehicleList.api

import com.example.hometask.vehicleList.api.models.RemoteVehicle
import com.example.hometask.vehicleList.api.models.RemoteVehiclesNote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface VehiclesApi {

    @GET("//private-fe87c-simpleclassifieds.apiary-mock.com/")
    fun getVehicles(): Single<List<RemoteVehicle>>

    @GET("//private-e7c3d8-classifiednotes.apiary-mock.com/")

    fun getVehiclesNotes (): Single<List<RemoteVehiclesNote>>

}
