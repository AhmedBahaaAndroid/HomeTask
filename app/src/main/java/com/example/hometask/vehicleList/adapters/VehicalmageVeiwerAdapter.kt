package com.example.hometask.vehicleList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hometask.R
import com.example.hometask.databinding.VehicalImageItemBinding

class VehicalmageVeiwerAdapter(
    private var images: List<String>
) :
    RecyclerView.Adapter<VehicalmageVeiwerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class ViewHolder private constructor(private val binding: VehicalImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                Glide.with(this@ViewHolder.itemView.context)
                    .asBitmap()
                    .placeholder(R.drawable.vehicle_placeholder)
                    .load(item)
                    .into(vehicleImage)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    VehicalImageItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}