package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.grocerystoreapp.databinding.FragmentProductDetailsBinding
import com.example.grocerystoreapp.databinding.LoadingItemBinding
import com.example.grocerystoreapp.model.ProductData
import com.example.grocerystoreapp.model.UIState

class ProductDetailsFragment: ViewModelFragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.productLiveData.observe(viewLifecycleOwner){ uistate ->
            when(uistate){
                is UIState.Loading -> {
                    viewModel.setSuccess(args.productData)
                }
                is UIState.Error -> {

                }

                is UIState.Success<*> -> {
                    val item = uistate.response as ProductData
                    binding.apply {
                        tvDetailAvailability.text = item.items[1].fulfillment.inStore.toString()
                        tvDetailPrice.text = item.items[3].price.regular.toString()
                        tvDetailDescription.text = item.description

                        Glide.with(ivDetailImage)
                            .load(item.images[2].sizes[1].url)
                            .into(ivDetailImage)
                    }

                }
            }
        }
    }
}