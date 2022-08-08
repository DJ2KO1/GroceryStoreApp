package com.example.grocerystoreapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.grocerystoreapp.databinding.LocationListItemBinding
import com.example.grocerystoreapp.model.LocationData

class LocationAdapter (
    private val list: MutableList<LocationData> = mutableListOf()
): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>(){

    fun setLocationList(newList: List<LocationData>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class LocationViewHolder(private val binding: LocationListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: LocationData){
            binding.apply {
                tvAddress.text = item.address.addressLine1.plus(item.address.city).plus(item.address.county).plus(item.address.state).plus(item.address.zipCode)
                tvName.text = item.name
                tvHours.text = item.hours.monday.open
            }
        }
    }
}