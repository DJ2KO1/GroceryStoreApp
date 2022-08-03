package com.example.grocerystoreapp.api

import com.example.grocerystoreapp.MainActivity
import com.example.grocerystoreapp.model.ProductResponse
import com.example.grocerystoreapp.model.TokenResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Body

interface APIService {
    @Headers("Accept: application/json")
    @GET("v1/products")
    suspend fun getProducts(
        @Header("Authorization") bearer: String,
        @Query("locationId") locationId: Long ,
        @Query("term") term: String
    ): Response<ProductResponse>

    @POST("v1/connect/oauth2/token")
     fun getToken(@Body body: MainActivity.Body ): Call<TokenResponse>
}


//    https://api.kroger.com/v1/connect/oauth2/token



//Headers
//( "Accept: application/json")
//("Authorization: Bearer $token")
//grant_type: "client_credentials"
//scope: "product.compact profile.compact"
//client_id: "grocerystore-e32339f7148fa55f5dba413bc44b21f24829451287186067310"
//client_secret: "pQEWKEi84uuHbGCDfrcZXunsOLyYS1UX5SEr7Tgq"