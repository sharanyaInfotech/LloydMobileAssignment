package com.assignment.test.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.test.R
import com.assignment.test.databinding.LayoutVehicleListIemBinding
import com.assignment.test.models.VehicleItem

class VehiclesAdapter(): RecyclerView.Adapter<VehiclesAdapter.VehicleViewHolder>() {

    class VehicleViewHolder(val binding: LayoutVehicleListIemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicleItem: VehicleItem) {
            binding.vehicleData = vehicleItem
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<VehicleItem>() {
        override fun areItemsTheSame(oldItem: VehicleItem, newItem: VehicleItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: VehicleItem, newItem: VehicleItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var vehicleList: List<VehicleItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        return VehicleViewHolder(
            LayoutVehicleListIemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicleList[position])
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

}