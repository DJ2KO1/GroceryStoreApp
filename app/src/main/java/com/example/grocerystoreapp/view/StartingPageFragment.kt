package com.example.grocerystoreapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.grocerystoreapp.databinding.FragmentStartingPageBinding
import com.example.grocerystoreapp.di.DI
import com.example.grocerystoreapp.view.MainActivity.Body.tokenrequest
import com.google.firebase.auth.FirebaseAuth


class StartingPageFragment: ViewModelFragment() {

    private lateinit var binding: FragmentStartingPageBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()

        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            //needs to validate login
            if(login()) {
                DI.getApiService(context)
                tokenrequest = true

                findNavController().navigate(
                    StartingPageFragmentDirections.actionNavStartingPageToNavZipcodePage(
                    )
                )
            } else Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }

        binding.tvRedirectRegistration.setOnClickListener {
            findNavController().navigate(StartingPageFragmentDirections.actionNavStartingPageToNavRegistrationPage())
        }
        return binding.root
    }

    private fun login():Boolean {
        val email = binding.etUsernameLogin.toString()
        val pass = binding.etPasswordLogin.toString()
        var success = true

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful){
                success = true
            }
        }
        return success
    }
}