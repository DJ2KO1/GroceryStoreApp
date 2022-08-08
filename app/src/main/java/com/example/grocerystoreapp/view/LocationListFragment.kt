package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.grocerystoreapp.databinding.FragmentLocationListBinding
import com.example.grocerystoreapp.databinding.LocationListItemBinding
import com.example.grocerystoreapp.model.*

class LocationListFragment: ViewModelFragment() {

    private lateinit var binding: FragmentLocationListBinding
    lateinit var args: LocationListFragmentArgs

    private val locationAdapter by lazy {
        LocationAdapter(openSearch = ::openSearch)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> {
                    viewModel.getLocation(args.zipCode)
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = "Invalid ZipCode"
                    binding.tvLoadingText.textSize = 40F
                    Toast.makeText(context, "Please Try Again", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        binding.tvLoadingText.visibility = View.GONE
                        locationAdapter.setLocationList((it.response as LocationResponse).locationData)
                        rvLocations.adapter = locationAdapter
                    }
                }
                else -> {}
            }
        }
    }
    private fun openSearch(locationData: LocationData) {
        viewModel.setLoadingForLocation()
        findNavController().navigate(
            LocationListFragmentDirections.actionNavLocationListToNavSearchPage(locationData.locationId)
        )

    }

    }
