package com.example.grocerystoreapp.api

import com.example.grocerystoreapp.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {
    @Headers("Accept: application/json")
    @GET("v1/products")
    suspend fun getProducts(
        @Header("Authorization") bearer: String,
        @Query("locationId") locationId: Long ,
        @Query("term") term: String
    ): Response<ProductResponse>
}




//Headers
//( "Accept: application/json")
//("Authorization: Bearer $token")
//client id : grocerystore-e32339f7148fa55f5dba413bc44b21f24829451287186067310
//client secret : pQEWKEi84uuHbGCDfrcZXunsOLyYS1UX5SEr7Tgq