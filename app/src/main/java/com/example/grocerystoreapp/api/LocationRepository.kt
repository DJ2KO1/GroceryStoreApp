package com.example.grocerystoreapp.api

import com.example.grocerystoreapp.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface LocationRepository {
    suspend fun getLocations (zipcode: String): Flow<UIState>
}

class LocationRepositoryImpl(private val service: APIService): LocationRepository {
    override suspend fun getLocations(zipcode: String): Flow<UIState> =
        flow {
            try {
                val response = service.getLocations(zipcode)
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
