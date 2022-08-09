package com.example.grocerystoreapp.api

import android.content.Context
import androidx.appcompat.resources.Compatibility
import com.example.grocerystoreapp.model.SessionManager
import com.example.grocerystoreapp.model.UIState
import com.example.grocerystoreapp.view.MainActivity
import com.example.grocerystoreapp.view.MainActivity.Body.scope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Credentials

interface ProductRepository {
    suspend fun getProducts (locationId: String, term: String,context: Context): Flow<UIState>
}

class ProductRepositoryImpl(private val service: APIService): ProductRepository {
    private lateinit var sessionManager: SessionManager

    override suspend fun getProducts(locationId: String, term: String, context: Context): Flow<UIState> =
        flow {
            sessionManager = SessionManager(context)
            sessionManager.deleteAuthToken()
            val response = service.getToken(
                Credentials.basic(
                    MainActivity.client_id,
                    MainActivity.client_secret
                ), MainActivity.grant_type, scope
            )
            if (response.code() == 200){
                sessionManager.saveAuthToken(response.body()!!.access_token)
                MainActivity.tokenrequest = false
                try {
                    val productCall = service.getProducts(locationId, term)
                    if (productCall.code() == 200){
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