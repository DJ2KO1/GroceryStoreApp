package com.example.grocerystoreapp.api

import androidx.appcompat.resources.Compatibility
import com.example.grocerystoreapp.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ProductRepository {
    suspend fun getProducts (locationId: String, term: String): Flow<UIState>
}

class ProductRepositoryImpl(private val service: APIService): ProductRepository {
    override suspend fun getProducts(locationId: String, term: String): Flow<UIState> =
        flow {
            try {
                val response = service.getProducts(locationId, term)
                if (response.code()== 200){
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Empty Response"))
            } else throw Exception ("Failed Network Call")
        } catch (e: java.lang.Exception){
            emit(UIState.Error(e))
        }
    }
}
