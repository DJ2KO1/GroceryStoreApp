package com.example.grocerystoreapp.di

import retrofit2.Retrofit

object DI {
    private val service = Retrofit.Builder()
        .baseUrl("https://api.kroger.com")
        .header
}