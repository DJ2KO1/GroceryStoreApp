package com.example.grocerystoreapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.grocerystoreapp.databinding.RegistrationActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationFragment: ViewModelFragment() {
    private lateinit var binding: RegistrationActivityBinding


    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationActivityBinding.inflate(layoutInflater)

        // Initialising auth object
        auth = Firebase.auth

        binding.btnSSigned.setOnClickListener {
            if (signUpUser())
                findNavController().navigate(
                    RegistrationFragmentDirections.actionNavRegistrationPageToNavStartingPage()
                )
        }

        // switching from signUp Activity to Login Activity
        binding.tvRedirectLogin.setOnClickListener {
            findNavController().navigate(
                RegistrationFragmentDirections.actionNavRegistrationPageToNavStartingPage()
            )
        }
        return binding.root
    }

    private fun signUpUser(): Boolean {
        val email = binding.etSEmailAddress.text.toString()
        val pass = binding.etSPassword.text.toString()
        val confirmPassword = binding.etSConfPassword.text.toString()
        var signedUP = false

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
        }

        if (pass != confirmPassword) {
            Toast.makeText(context, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()

        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener() {
            if (it.isSuccessful) {
                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                signedUP = true
            } else {
                Toast.makeText(context, "Account Not Created", Toast.LENGTH_SHORT).show()
            }
        }
        return signedUP
    }
}