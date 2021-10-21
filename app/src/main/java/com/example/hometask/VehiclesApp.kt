package com.example.hometask

import android.app.Application
import com.example.hometask.diModules.homeModule
import com.example.hometask.diModules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VehiclesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VehiclesApp)
            modules( networkModule + homeModule)
             }

    }
}