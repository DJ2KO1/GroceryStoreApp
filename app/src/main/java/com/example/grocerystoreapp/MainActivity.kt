package com.example.grocerystoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grocerystoreapp.api.APIService
import com.example.grocerystoreapp.di.DI
import com.example.grocerystoreapp.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: DI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        info = apiClient.service.getToken(Body)
        sessionManager.saveAuthToken(TokenResponse.)




//            login(LoginRequest(email = "s@sample.com", password = "mypassword"))
//            enqueue(object : Response<TokenResponse> {
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    // Error logging in
//                }
//
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    val loginResponse = response.body()
//
//                    if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
//                        sessionManager.saveAuthToken(loginResponse.authToken)
//                    } else {
//                        // Error logging in
//                    }
//                }
//            })

    }

    companion object Body{
        val grant_type: String = "client_credentials"
        val  scope: String = "product.compact profile.compact"
        val  client_id: String =  "grocerystore-e32339f7148fa55f5dba413bc44b21f24829451287186067310"
        val  client_secret: String = "pQEWKEi84uuHbGCDfrcZXunsOLyYS1UX5SEr7Tgq"

    }
}