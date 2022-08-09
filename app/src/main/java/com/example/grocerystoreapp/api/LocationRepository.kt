package com.example.grocerystoreapp.api

import android.content.Context
import com.example.grocerystoreapp.model.SessionManager
import com.example.grocerystoreapp.model.UIState
import com.example.grocerystoreapp.view.MainActivity.Body.client_id
import com.example.grocerystoreapp.view.MainActivity.Body.client_secret
import com.example.grocerystoreapp.view.MainActivity.Body.grant_type
import com.example.grocerystoreapp.view.MainActivity.Body.tokenrequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Credentials


interface LocationRepository {
    suspend fun getLocations (zipcode: String, context: Context): Flow<UIState>
}

class LocationRepositoryImpl(private val service: APIService): LocationRepository {

    private lateinit var sessionManager: SessionManager
    override suspend fun getLocations(zipcode: String, context: Context): Flow<UIState> =
        flow {
            val response = service.getToken(Credentials.basic(client_id, client_secret), grant_type)
            if (response.code() == 200){

                sessionManager = SessionManager(context)

                sessionManager.saveAuthToken(response.body()!!.access_token)
                println(sessionManager.fetchAuthToken().toString())
                tokenrequest = false
                try {
                    val locationCall = service.getLocations(zipcode)
                    if (locationCall.code()== 200){
                        emit(locationCall.body()?.let {
                            UIState.Success(it)
                        } ?: throw Exception("Empty Response"))
                    } else throw Exception ("Failed Network Call")
                    println(locationCall.body().toString())
                } catch (e: java.lang.Exception){
                    emit(UIState.Error(e))
                }
            } else throw Exception ("Failed Network Call")
        }
}