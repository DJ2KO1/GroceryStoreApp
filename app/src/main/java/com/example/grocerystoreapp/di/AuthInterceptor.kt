package com.example.grocerystoreapp.di

import android.content.Context
import com.example.grocerystoreapp.model.SessionManager
import com.example.grocerystoreapp.view.MainActivity.Body.tokenrequest
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context?) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()

        // If token has been saved, add it to the request

            sessionManager.fetchAuthToken()?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }


        return chain.proceed(requestBuilder.build())
    }
}