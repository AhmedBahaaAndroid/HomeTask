package com.example.hometask.vehicleList.repo

import com.example.hometask.falkeVehiclesList
import com.example.hometask.vehicleList.api.VehiclesApi
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
internal class VehiclesRepositoryTest {
    private lateinit var vehiclesRepository: VehiclesRepository

    @Mock
    private lateinit var vehiclesApi: VehiclesApi


    @BeforeEach
    fun setUp() {
        vehiclesRepository = VehiclesRepository(vehiclesApi)
    }

    @Test
    fun `when get vehicle then vehicle list is returned with list `() {
        Mockito.`when`(vehiclesApi.getVehicles()).thenReturn(Single.just(falkeVehiclesList))
        val actualValue = vehiclesRepository.getVehicles()
        assertNotNull(
            actualValue
        )
    }

}