package com.example.grocerystoreapp.api

import com.example.grocerystoreapp.model.LocationResponse
import com.example.grocerystoreapp.model.ProductResponse
import com.example.grocerystoreapp.model.TokenResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    //  'https://api.kroger.com/v1/products?filter.brand={{BRAND}}&filter.term={{TERM}}&filter.locationId={{LOCATION_ID}}' \
    @Headers("Accept: application/json")
    @GET("v1/products")
     suspend fun getProducts(
        @Query("filter.locationId") locationId: String ,
        @Query("filter.term") term: String
    ): Response<ProductResponse>

     @GET("v1/locations")
     suspend fun getLocations(
         @Query("filter.zipCode.near") zipcode: String,
         @Query("filter.chain") chain: String = "Kroger"
     ): Response<LocationResponse>

    @FormUrlEncoded
    @POST("v1/connect/oauth2/token")
   suspend fun getToken(
        @Header("Authorization")  authorization: String,
        @Field("grant_type") grant_type: String,
        @Field("scope") scope:String
    ): Response<TokenResponse>
}


//    https://api.kroger.com/v1/connect/oauth2/token



//Headers
//( "Accept: application/json")
//("Authorization: Bearer $token")
//grant_type: "client_credentials"
//scope: "product.compact profile.compact"
//client_id: "grocerystore-e32339f7148fa55f5dba413bc44b21f24829451287186067310"
//client_secret: "pQEWKEi84uuHbGCDfrcZXunsOLyYS1UX5SEr7Tgq"