package com.authapp.api.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://easypay.world/"

    private val moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

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