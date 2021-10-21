package com.example.hometask.vehicleList

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hometask.R
import com.example.hometask.base.BaseActivity
import com.example.hometask.databinding.ActivityMainBinding
import com.example.hometask.vehicleList.fragments.VehicleListFragment
import com.example.hometask.vehicleList.viewModels.VehicleViewModel

class MainActivity : BaseActivity<VehicleViewModel>(VehicleViewModel::class) {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.bind(contentLayout) ?: return
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, VehicleListFragment())
            .commitNow()
        binding.lifecycleOwner = this
    }
}