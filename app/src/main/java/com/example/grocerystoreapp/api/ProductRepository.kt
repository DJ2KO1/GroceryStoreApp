package com.example.grocerystoreapp.api

import androidx.appcompat.resources.Compatibility
import com.example.grocerystoreapp.model.UIState
import com.example.grocerystoreapp.view.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Credentials

interface ProductRepository {
    suspend fun getProducts (locationId: String, term: String): Flow<UIState>
}

class ProductRepositoryImpl(private val service: APIService): ProductRepository {
    override suspend fun getProducts(locationId: String, term: String): Flow<UIState> =
        flow {
            val response = service.getToken(
                Credentials.basic(
                    MainActivity.client_id,
                    MainActivity.client_secret
                ), MainActivity.grant_type
            )
            if (response.code() == 200){
                try {
                    val productCall = service.getProducts(locationId, term)
                    if (productCall.code()== 200){
                        emit(productCall.body()?.let {
                            UIState.Success(it)
                        } ?: throw Exception("Empty Response"))
                    } else throw Exception ("Failed Network Call")
                } catch (e: java.lang.Exception){
                    emit(UIState.Error(e))
                }
            } else throw Exception ("Failed Network Call")
        }
}