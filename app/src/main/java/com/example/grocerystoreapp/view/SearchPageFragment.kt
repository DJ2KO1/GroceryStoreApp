package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.grocerystoreapp.databinding.FragmentSearchPageBinding

class SearchPageFragment: ViewModelFragment() {
    private lateinit var binding: FragmentSearchPageBinding
    private val args: SearchPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPageBinding.inflate(layoutInflater)

        binding.btnSearch.setOnClickListener {
            viewModel.setLoading()

            findNavController().navigate(
                SearchPageFragmentDirections.actionNavSearchPageToNavProductList(
                    binding.etSearchTerms.text.toString(),
                    args.locationData

                )
            )
        }
        return binding.root
    }
}