package com.authapp.api.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthInfo(
    @Json(name = "response")
    val tokenInfo: TokenInfo
)