package com.authapp.api.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @POST("/login")
    suspend fun getAuthInfo(@Body getBody: AuthProfileData): Call<AuthInfo>

}