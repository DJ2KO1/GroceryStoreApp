package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.grocerystoreapp.databinding.FragmentStartingPageBinding

class StartingPageFragment: ViewModelFragment() {

    private lateinit var binding: FragmentStartingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            viewModel.setLoading()
            findNavController().navigate(
                StartingPageFragmentDirections.actionNavStartingPageToNavZipcodePage(
                )
            )
        }
        return binding.root
    }
}