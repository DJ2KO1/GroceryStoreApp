package com.example.grocerystoreapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.grocerystoreapp.api.APIService
import com.example.grocerystoreapp.api.LocationRepositoryImpl
import com.example.grocerystoreapp.api.ProductRepositoryImpl
import com.example.grocerystoreapp.databinding.FragmentLocationListBinding
import com.example.grocerystoreapp.viewmodel.ProductViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonDisposableHandle.parent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {

    fun getApiService(context: Context?): APIService {

        return Retrofit.Builder()
            .baseUrl("https://api.kroger.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient(context))
            .build()
            .create(APIService::class.java)
    }

    private fun provideHttpClient(context: Context?): OkHttpClient {
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

    private fun provideRepository(context: Context?) = ProductRepositoryImpl(getApiService(context))
    private fun provideDispatcher() = Dispatchers.IO
    private fun provideLocationRepository(context: Context?) = LocationRepositoryImpl(getApiService(context))

    fun provideViewModel(storeOwner: ViewModelStoreOwner, context: Context?): ProductViewModel {
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProductViewModel(provideRepository(context), provideDispatcher(), provideLocationRepository(context)) as T
            }
        })[ProductViewModel::class.java]
    }
}