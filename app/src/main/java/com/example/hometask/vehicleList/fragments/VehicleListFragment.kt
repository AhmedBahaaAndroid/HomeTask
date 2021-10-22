package com.example.hometask.vehicleList.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometask.R
import com.example.hometask.base.BaseFragment
import com.example.hometask.databinding.VehicleListFragmentBinding
import com.example.hometask.vehicleList.adapters.VehiclesAdapter
import com.example.hometask.vehicleList.adapters.VehiclesClickListener
import com.example.hometask.vehicleList.domain.models.toVehicleUIModel
import com.example.hometask.vehicleList.viewModels.VehicleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehicleListFragment : BaseFragment<VehicleViewModel>(VehicleViewModel::class) {
    private lateinit var binding: VehicleListFragmentBinding
    private val viewModel by viewModel<VehicleViewModel>()

    private val vehiclesAdapter = VehiclesAdapter(VehiclesClickListener { vehicleId ->
        viewModel.onSelectedVehicle(vehicleId)
    })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = VehicleListFragmentBinding.inflate(inflater, container, false)
        return attachToRootView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        with(binding.vehicalListRecyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = vehiclesAdapter
            itemAnimator = null
        }

        viewModel.vehiclesList.observe(viewLifecycleOwner, Observer { vehicleDetailsList ->
            vehiclesAdapter.submitList(vehicleDetailsList.map { it.toVehicleUIModel() })
        })

        viewModel.selectedVehicle.observe(viewLifecycleOwner, Observer {
            if (!it.images.isNullOrEmpty())
                showVehicleImagesView(it.images)
        })
    }

    private fun showVehicleImagesView(imageList: List<String>) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.vehicles_containr_frame,
                VehicleImagesViewerFragment.newInstance(imageList as ArrayList<String>)
            ).addToBackStack("ImageViewer").commit()
    }
}
