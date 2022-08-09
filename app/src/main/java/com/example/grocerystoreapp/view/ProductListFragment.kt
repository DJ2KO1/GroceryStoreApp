package com.example.grocerystoreapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.grocerystoreapp.databinding.FragmentProductListBinding
import com.example.grocerystoreapp.model.ProductData
import com.example.grocerystoreapp.model.ProductResponse
import com.example.grocerystoreapp.model.UIState

class ProductListFragment : ViewModelFragment(){
    private lateinit var binding: FragmentProductListBinding
    val args: ProductListFragmentArgs by navArgs()

    private val productAdapter by lazy {
        ProductAdapter(openDetails = ::openDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        configureObserver(activity!!.applicationContext)
        return binding.root
    }

    private fun configureObserver(context: Context) {
        viewModel.productLiveData.observe(viewLifecycleOwner) { uistate ->
            when(uistate) {
                is UIState.Loading -> {
                    viewModel.getKrogerProducts(args.locationData.locationId.toString(), args.term, context)
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = "Invalid Product"
                    binding.tvLoadingText.textSize = 40F
                    Toast.makeText(context, "Please Try Again", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        binding.tvLoadingText.visibility = View.GONE
                        productAdapter.setProductList((uistate.response as ProductResponse).data)
                        rvProducts.adapter = productAdapter
                    }
                }
                else -> {}
            }
        }
    }

    private fun openDetails(productData: ProductData) {
        viewModel.setLoadingForDetails()
        findNavController().navigate(
            ProductListFragmentDirections.actionNavProductListToNavProductDetails(
                productData,
                args.locationData
            )
        )

    }
}