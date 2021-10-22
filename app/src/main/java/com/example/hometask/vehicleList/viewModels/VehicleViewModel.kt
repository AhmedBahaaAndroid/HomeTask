package com.example.hometask.vehicleList.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hometask.base.BaseViewModel
import com.example.hometask.vehicleList.domain.GetVehiclesDetails
import com.example.hometask.vehicleList.domain.models.VehicleDetails

class VehicleViewModel(
    getVehiclesDetails: GetVehiclesDetails
) : BaseViewModel() {

    private val _selectedVehicle = MutableLiveData<VehicleDetails>()
    val selectedVehicle: LiveData<VehicleDetails>
        get() = _selectedVehicle
    private val _vehiclesList = MutableLiveData<List<VehicleDetails>>()
    val vehiclesList: LiveData<List<VehicleDetails>>
        get() = _vehiclesList

    init {
        getVehiclesDetails()
            .subscribeWithViewState({
                _vehiclesList.value = it
            })
    }

    fun onSelectedVehicle(vehicleId: Int) {
        _selectedVehicle.value = _vehiclesList.value?.find { it.id == vehicleId }
    }

}