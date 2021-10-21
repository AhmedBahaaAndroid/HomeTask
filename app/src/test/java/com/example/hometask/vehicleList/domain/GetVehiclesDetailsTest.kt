package com.example.hometask.vehicleList.domain

import com.example.hometask.InstantExecutorExtension
import com.example.hometask.TestSchedulerExtension
import com.example.hometask.fakeVehicleDetails
import com.example.hometask.vehicleList.api.models.Image
import com.example.hometask.vehicleList.api.models.RemoteVehicle
import com.example.hometask.vehicleList.api.models.RemoteVehiclesNote
import com.example.hometask.vehicleList.domain.models.VehicleDetails
import com.example.hometask.vehicleList.repo.VehiclesRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(
    InstantExecutorExtension::class,
    MockitoExtension::class,
    TestSchedulerExtension::class

)
internal class GetVehiclesDetailsTest {
    private lateinit var getVehiclesDetails: GetVehiclesDetails

    @Mock
    private lateinit var vehiclesRepository: VehiclesRepository

    @BeforeEach
    fun setUp(){
        getVehiclesDetails = GetVehiclesDetails(vehiclesRepository)
    }

    @Test
    fun `when get vehicle  then vehicle list   is returned with list `() {
       // Mockito.`when`(vehiclesRepository.getVehicles()).thenReturn(Single.just(falkeVehiclesList))
       // Mockito.`when`(vehiclesRepository.getVehiclesNotes())
         //   .thenReturn(Single.just(fakeVehicleNote))
        val actualValue = getVehiclesDetails.invoke()
        Assert.assertEquals(
            fakeVehicleDetails,
            actualValue
        )
    }
}