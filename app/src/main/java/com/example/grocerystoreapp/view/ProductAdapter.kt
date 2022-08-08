package com.example.grocerystoreapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.grocerystoreapp.databinding.LoadingItemBinding
import com.example.grocerystoreapp.databinding.ProductListItemBinding
import com.example.grocerystoreapp.model.ProductData

class ProductAdapter(
    private val list: MutableList<ProductData> = mutableListOf(),
    private val openDetails: (ProductData) -> Unit
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun setProductList(newList: List<ProductData>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ProductListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(item: ProductData){
            binding.apply {
                tvAvailability.text = item.items[1].fulfillment.inStore.toString()
                tvPrice.text = item.items[3].price.regular.toString()
                tvProductDescription.text = item.description

                Glide.with(ivProductImage)
                    .load(item.images[2].sizes[1].url)
                    .into(ivProductImage)
            }

            binding.root.setOnClickListener {
                openDetails(item)
            }
        }
    }

    inner class LoadingViewHolder(private val binding: LoadingItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}