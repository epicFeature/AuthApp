package com.authapp.api.auth

import com.authapp.api.common.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class AuthService(private val repository: AuthRepository) {
    private val retrofit = RetrofitInstance.instance

    suspend fun getAuthData(getBody:AuthProfileData) =
        retrofit
        .create(AuthApi::class.java)
        .getAuthInfo(getBody)

    fun saveToken(token:String){
        repository.saveToken(token)
    }
}