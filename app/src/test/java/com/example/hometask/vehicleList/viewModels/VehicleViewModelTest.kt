package com.example.hometask.vehicleList.viewModels

import com.example.hometask.InstantExecutorExtension
import com.example.hometask.TestSchedulerExtension
import com.example.hometask.fakeVehicleDetails
import com.example.hometask.vehicleList.domain.GetVehiclesDetails
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(
    InstantExecutorExtension::class,
    TestSchedulerExtension::class,
    MockitoExtension::class
)
class VehicleViewModelTest {
    private lateinit var vehicleViewModel: VehicleViewModel

    @Mock
    private lateinit var getVehiclesDetails: GetVehiclesDetails

    @BeforeEach
    fun setUp() {
        Mockito.`when`(getVehiclesDetails()).thenReturn(Single.just(fakeVehicleDetails))
        vehicleViewModel = VehicleViewModel(getVehiclesDetails)
    }

    @Test
    fun `when get vehicle details  then vehicle list  is returned with all required info `() {
        Assertions.assertEquals(
            fakeVehicleDetails,
            vehicleViewModel.vehiclesList.value
        )
    }

    @Test
    fun `when selected vehicle by id then vehicle selected returned with details `() {
        vehicleViewModel.onSelectedVehicle(1)
        Assertions.assertEquals(
            fakeVehicleDetails[0],
            vehicleViewModel.selectedVehicle.value
        )
    }
}