package com.example.grocerystoreapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerystoreapp.databinding.LocationListItemBinding
import com.example.grocerystoreapp.model.Data

class LocationAdapter (
    private val list: MutableList<Data> = mutableListOf(),
    private val openSearch:(Data) -> Unit
): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>(){

    fun setLocationList(newList: List<Data>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class LocationViewHolder(private val binding: LocationListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: Data){
            binding.apply {
                println(item.locationId)
                tvAddress.text = item.address?.addressLine1.plus(item.address?.city).plus(item.address?.county).plus(item.address?.state).plus(item.address?.zipCode)
                tvName.text = item.name
                tvHours.text = item.hours?.monday?.open
            }
            binding.root.setOnClickListener {
                openSearch(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}