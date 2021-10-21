package com.example.hometask.vehicleList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hometask.R
import com.example.hometask.databinding.VehicalItemViewBinding
import com.example.hometask.vehicleList.uiModel.VehicleUIModel

class VehiclesAdapter(private val clickListener: VehiclesClickListener) :
    ListAdapter<VehicleUIModel, VehiclesAdapter.ViewHolder>(VehiclesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, position, clickListener)
        }
    }

    class ViewHolder private constructor(private val binding: VehicalItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: VehicleUIModel,
            position: Int,
            clickListener: VehiclesClickListener
        ) {
            with(binding) {
                Glide.with(this@ViewHolder.itemView.context)
                    .asBitmap()
                    .placeholder(R.drawable.vehicle_placeholder)
                    .load(item.photo?.first())
                    .into(vehiclImage)
                vehicleFuel.text = item.fuelType
                vehicleName.text = item.title
                vehiclePrice.text = item.price.toString()
                if (item.note.isNullOrEmpty()
                ) {
                    vehicleNoteLabel.visibility = View.GONE
                } else {
                    vehicleNoteLabel.visibility = View.VISIBLE
                    vehicleNote.text = item.note
                }
                executePendingBindings()
                root.setOnClickListener {
                    clickListener.onClick(position)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    VehicalItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class VehiclesClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(position: Int) = clickListener(position)
}

class VehiclesDiffCallback : DiffUtil.ItemCallback<VehicleUIModel>() {

    override fun areItemsTheSame(
        oldItem: VehicleUIModel,
        newItem: VehicleUIModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: VehicleUIModel,
        newItem: VehicleUIModel
    ): Boolean {
        return oldItem == newItem
    }
}


