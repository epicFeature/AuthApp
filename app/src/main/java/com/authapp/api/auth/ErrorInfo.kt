package com.authapp.api.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorInfo(
    @Json(name = "error_msg")
    val errorMsg: String
)
