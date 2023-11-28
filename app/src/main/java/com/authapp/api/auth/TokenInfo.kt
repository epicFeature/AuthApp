package com.authapp.api.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenInfo(
    val token:String
)
