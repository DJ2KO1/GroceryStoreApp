package com.example.grocerystoreapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerystoreapp.databinding.FragmentLocationListBinding
import com.example.grocerystoreapp.databinding.LocationListItemBinding
import com.example.grocerystoreapp.model.*

class LocationListFragment: ViewModelFragment() {

    private lateinit var binding: FragmentLocationListBinding
    private val args: LocationListFragmentArgs by navArgs()

    private val locationAdapter by lazy {
        LocationAdapter(openSearch = ::openSearch)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationListBinding.inflate(layoutInflater)
        println("This is the Location list")
        configureObserver(activity!!.applicationContext)
        return binding.root
    }

    private fun configureObserver(context: Context) {
        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> {
                    println("This is the Location list Loading")
                    viewModel.getLocation(args.zipCode,context)
                    binding.rvLocations.adapter = locationAdapter

                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = "Invalid ZipCode"
                    binding.tvLoadingText.textSize = 40F
                    Toast.makeText(context, "Please Try Again", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success<*> -> {
                    println("This is the Location list Success")

                    binding.apply {
                        pbLoading.visibility = View.GONE
                        binding.tvLoadingText.visibility = View.GONE
                        println()
                        locationAdapter.setLocationList((it.response as LocationResponse).data)
                        rvLocations.adapter = locationAdapter
                    }
                }
                else -> {}
            }
        }
    }
    private fun openSearch(item: LocationItem) {
        viewModel.setLoadingForLocation()
        findNavController().navigate(
            LocationListFragmentDirections.actionNavLocationListToNavSearchPage(item)
        )

    }

    }
