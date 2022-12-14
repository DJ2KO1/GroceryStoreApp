package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.grocerystoreapp.api.LocationRepositoryImpl
import com.example.grocerystoreapp.databinding.FragmentZipcodePageBinding
import com.example.grocerystoreapp.di.DI

class ZipcodeFragment: ViewModelFragment() {

    private lateinit var binding: FragmentZipcodePageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZipcodePageBinding.inflate(layoutInflater)

        binding.btnFindStore.setOnClickListener {
            viewModel.setLoadingForLocation()
            findNavController().navigate(
                ZipcodeFragmentDirections.actionNavZipcodePageToNavLocationList(
                    binding.etZipcode.text.toString()
                )
            )
        }
        return binding.root
    }
}
