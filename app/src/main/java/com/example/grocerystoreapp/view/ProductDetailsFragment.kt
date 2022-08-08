package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.grocerystoreapp.databinding.FragmentProductDetailsBinding
import com.example.grocerystoreapp.databinding.LoadingItemBinding
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
                    binding.apply {

                    }

                }
            }
        }
    }
}