package com.example.hometask.vehicleList.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.example.hometask.databinding.FrgamentVehicalImagesViewerBinding
import com.example.hometask.vehicleList.adapters.VehicalmageVeiwerAdapter

class VehicleImagesViewerFragment : Fragment() {
    private lateinit var binding: FrgamentVehicalImagesViewerBinding
    private lateinit var vehicleImagesViewerAdapter: VehicalmageVeiwerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FrgamentVehicalImagesViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vehicleImages:List<String> =
            arguments?.getStringArrayList(VEHICLE_IMAGES_ARG)?.toList() ?: throw IllegalArgumentException ("Argument can't be null")
        setupViewPager(vehicleImages)
    }

    private fun setupViewPager(vehicleImages: List<String>) {
        vehicleImagesViewerAdapter = VehicalmageVeiwerAdapter(vehicleImages)
        binding.vehicalImagesViewpager.adapter = vehicleImagesViewerAdapter
        binding.vehicalImagesViewpager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        binding.vehicalImagesViewpager.setPageTransformer(compositePageTransformer)
    }

    companion object {
        const val VEHICLE_IMAGES_ARG = "vehicleImages"
        fun newInstance(vehicleImages: ArrayList<String>) =
            VehicleImagesViewerFragment().apply {
                val bundle = Bundle().apply {
                    putStringArrayList(VEHICLE_IMAGES_ARG, vehicleImages)
                }
                arguments = bundle
            }
    }
}