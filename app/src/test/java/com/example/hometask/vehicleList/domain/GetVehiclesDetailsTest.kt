package com.example.hometask.vehicleList.domain

import com.example.hometask.fakeVehicleDetails
import com.example.hometask.fakeVehicleNote
import com.example.hometask.falkeVehiclesList
import com.example.hometask.vehicleList.repo.VehiclesRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(
    MockitoExtension::class
)
internal class GetVehiclesDetailsTest {
    private lateinit var getVehiclesDetails: GetVehiclesDetails

    @Mock
    private lateinit var vehiclesRepository: VehiclesRepository

    @BeforeEach
    fun setUp() {
        getVehiclesDetails = GetVehiclesDetails(vehiclesRepository)
    }

    @Test
    fun `when get vehicle and vehicle notes  then vehicle list   is returned with list `() {
        Mockito.`when`(vehiclesRepository.getVehicles()).thenReturn(Single.just(falkeVehiclesList))
        Mockito.`when`(vehiclesRepository.getVehiclesNotes())
            .thenReturn(Single.just(fakeVehicleNote))

        val actualValue = getVehiclesDetails()
        Assert.assertEquals(
            fakeVehicleDetails,
            actualValue.blockingGet()
        )
    }
}