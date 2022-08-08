package com.example.grocerystoreapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.grocerystoreapp.api.APIService
import com.example.grocerystoreapp.api.LocationRepositoryImpl
import com.example.grocerystoreapp.api.ProductRepositoryImpl
import com.example.grocerystoreapp.viewmodel.ProductViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {
    private lateinit var apiService: APIService

    fun getApiService(context: Context): APIService {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.kroger.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideHttpClient(context))
                .build()

            apiService = retrofit.create(APIService::class.java)
        }

        return apiService
    }

    private fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRepository() = ProductRepositoryImpl(apiService)
    private fun provideDispatcher() = Dispatchers.IO
    private fun provideLocationRepository() = LocationRepositoryImpl(apiService)

    fun provideViewModel(storeOwner: ViewModelStoreOwner): ProductViewModel {
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProductViewModel(provideRepository(), provideDispatcher(), provideLocationRepository()) as T
            }
        })[ProductViewModel::class.java]
    }
}