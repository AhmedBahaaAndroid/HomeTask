package com.example.hometask.diModules

import com.example.hometask.vehicleList.api.VehiclesApi
import com.example.hometask.vehicleList.domain.GetVehiclesDetails
import com.example.hometask.vehicleList.repo.VehiclesRepository
import com.example.hometask.vehicleList.viewModels.VehicleViewModel
import com.example.hometask.network.NetworkErrorMapper
import com.example.hometask.network.RetrofitClient
import com.example.hometask.network.RxJavaErrorAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    factory { NetworkErrorMapper() }
    single {
        RetrofitClient.Builder("https://-fakebaseurl")
            .build {
                useDefaultLoggerInterceptor()
                setCustomConverterAdapter(getOrNull())
                addCallAdapter(
                    RxJavaErrorAdapterFactory.createWithScheduler(
                        Schedulers.io(),
                        get()
                    )
                )

            }
    }
}

val homeModule = module {
    single { get<RetrofitClient>().create(VehiclesApi::class.java) }
    factory { VehiclesRepository(get()) }
    factory { GetVehiclesDetails(get()) }
    viewModel { VehicleViewModel(get()) }
}