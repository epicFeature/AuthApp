package com.authapp.api.common

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://easypay.world/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.request().newBuilder()
                .addHeader("app-key", "12345")
                .addHeader("v", "1")
                .build()
                .let { chain.proceed(it) }
        }
        .build()

    val instance: Retrofit =
        Retrofit.Builder()
            .baseUrl(HttpUrl.get(BASE_URL))
            .addConverterFactory(
                MoshiConverterFactory.create()
            ).client(client)
            .build()
}