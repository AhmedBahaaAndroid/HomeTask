package com.example.hometask

import com.example.hometask.InstantExecutorExtension
import com.example.hometask.TestSchedulerExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(
    InstantExecutorExtension::class,
    MockitoExtension::class,
    TestSchedulerExtension::class
)
internal class RemoteVehicleViewModelTest {

//    private lateinit var viewModel: VehicleViewModel
//
//    @Mock
//    private lateinit var vehiclesRepository: VehiclesRepository
//
//    private val fakeCarsList = listOf(
//        RemoteVehicle(
//            id = "fake id 1",
//            name = "fake name",
//            modelIdentifier = "fake modelIdentifier",
//            modelName = "fake model",
//            maker = "fake maker",
//            photo = "fake photo url",
//            group = "fake group",
//            color = "fake color",
//            series = "fake series",
//            fuelLevel = "fake level",
//            fuelType = "fake type",
//            licensePlate = "fake palte number",
//            innerCleanliness = "fake cleanliness",
//            longitude = 1.00,
//            latitude = 2.00,
//            transmission = "fake transmission"
//        ),
//        RemoteVehicle(
//            id = "fake id 2",
//            name = "fake name",
//            modelIdentifier = "fake modelIdentifier",
//            modelName = "fake model",
//            maker = "fake maker",
//            photo = "fake photo url",
//            group = "fake group",
//            color = "fake color",
//            series = "fake series",
//            fuelLevel = "fake level",
//            fuelType = "fake type",
//            licensePlate = "fake palte number",
//            innerCleanliness = "fake cleanliness",
//            longitude = 1.00,
//            latitude = 2.00,
//            transmission = "fake transmission"
//        ),
//        RemoteVehicle(
//            id = "fake id 3",
//            name = "fake name",
//            modelIdentifier = "fake modelIdentifier",
//            modelName = "fake model",
//            maker = "fake maker",
//            photo = "fake photo url",
//            group = "fake group",
//            color = "fake color",
//            series = "fake series",
//            fuelLevel = "fake level",
//            fuelType = "fake type",
//            licensePlate = "fake palte number",
//            innerCleanliness = "fake cleanliness",
//            longitude = 1.00,
//            latitude = 2.00,
//            transmission = "fake transmission"
//        )
//    )
//
//    private val fakeCarUIModelList = listOf(
//        VehicleUIModel(
//            id = fakeCarsList[0].id,
//            name = fakeCarsList[0].name,
//            photo = fakeCarsList[0].photo,
//            latitude = fakeCarsList[0].latitude,
//            longitude = fakeCarsList[0].longitude
//        ),
//        VehicleUIModel(
//            id = fakeCarsList[1].id,
//            name = fakeCarsList[1].name,
//            photo = fakeCarsList[1].photo,
//            latitude = fakeCarsList[1].latitude,
//            longitude = fakeCarsList[1].longitude
//        ),
//        VehicleUIModel(
//            id = fakeCarsList[2].id,
//            name = fakeCarsList[2].name,
//            photo = fakeCarsList[2].photo,
//            latitude = fakeCarsList[2].latitude,
//            longitude = fakeCarsList[2].longitude
//        )
//    )
//
//    private val fakeCarDetails = CarDetailsUIModel(
//        id = fakeCarsList[2].id,
//        name = fakeCarsList[2].name,
//        modelIdentifier = fakeCarsList[2].modelIdentifier,
//        modelName = fakeCarsList[2].modelName,
//        maker = fakeCarsList[2].maker,
//        photo = fakeCarsList[2].photo,
//        group = fakeCarsList[2].group,
//        color = fakeCarsList[2].color,
//        series = fakeCarsList[2].series,
//        fuelLevel = fakeCarsList[2].fuelLevel,
//        fuelType = fakeCarsList[2].fuelType,
//        licensePlate =fakeCarsList[2].licensePlate,
//        innerCleanliness = fakeCarsList[2].innerCleanliness,
//        transmission = fakeCarsList[2].transmission
//
//    )
//
//    @BeforeEach
//    fun setUp() {
//        Mockito.`when`(vehiclesRepository.getVehicles()).thenReturn(Single.just(fakeCarsList))
//        viewModel = VehicleViewModel(vehiclesRepository)
//    }
//
//    @Test
//    fun `when get cats  then carsList is returned with list of cars`() {
//        viewModel.getVehiclesDetails()
//        Assert.assertEquals(
//            fakeCarUIModelList,
//            viewModel.carsList.value
//        )
//    }
//
//    @Test
//    fun `when get cars details  then car detailsUI Modle is returned `() {
//        viewModel.getVehiclesDetails()
//        viewModel.moveToCarDetails(2)
//        Assert.assertEquals(
//            fakeCarDetails,
//            viewModel.carDetailsEvent.value
//        )
//    }
//
//
//    @Test
//    fun `when select car  then return selected CarUIModel  is returned `() {
//        viewModel.getVehiclesDetails()
//        viewModel.moveToSelectedCar(2)
//        Assert.assertEquals(
//            fakeCarUIModelList[2],
//            viewModel.selectedCar.value
//        )
//    }
}
