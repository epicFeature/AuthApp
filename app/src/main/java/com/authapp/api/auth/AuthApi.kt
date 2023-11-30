package com.authapp.api.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @POST("api-test/login")
    suspend fun getAuthInfo(@Body data: AuthProfileData): Response<AuthInfo>
}