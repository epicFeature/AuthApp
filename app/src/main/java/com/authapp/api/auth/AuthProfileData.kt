package com.authapp.api.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthProfileData(
    val login: String,
    val password: String
)
