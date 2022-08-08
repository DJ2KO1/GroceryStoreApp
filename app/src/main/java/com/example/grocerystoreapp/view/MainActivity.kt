package com.example.grocerystoreapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grocerystoreapp.R
import com.example.grocerystoreapp.model.SessionManager
import com.example.grocerystoreapp.di.ApiClient
import com.example.grocerystoreapp.di.DI

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: DI



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)


//            apiClient.getApiService(this).getToken(Credentials.basic(client_id, client_secret), grant_type).enqueue(object : Callback<TokenResponse> {
//                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
//                    val token = response.body()
//                    sessionManager.saveAuthToken(token!!.access_token)
//                    println(token!!.access_token.toString())
//                   println(sessionManager.fetchAuthToken().toString())
//
//                }
//
//            })

        apiClient.getApiService(this).getProducts(sessionManager.fetchAuthToken().toString())

    }

    companion object Body{
        val grant_type: String = "client_credentials"
        val  scope: String = "product.compact profile.compact"
        val  client_id: String =  "grocerystore-e32339f7148fa55f5dba413bc44b21f24829451287186067310"
        val  client_secret: String = "pQEWKEi84uuHbGCDfrcZXunsOLyYS1UX5SEr7Tgq"

    }
}