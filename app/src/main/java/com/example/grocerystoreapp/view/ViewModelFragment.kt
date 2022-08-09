package com.example.grocerystoreapp.view

import androidx.fragment.app.Fragment
import com.example.grocerystoreapp.di.DI

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity(),activity)
    }
}