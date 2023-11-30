package com.authapp.api.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonQualifier

@JsonClass(generateAdapter = true)
data class AuthInfo(
    val success: String,
    @Json(name = "response")
    val tokenInfo: TokenInfo?,
    @Json(name = "error")
    val errorInfo: ErrorInfo?
)