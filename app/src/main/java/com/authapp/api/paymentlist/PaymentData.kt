package com.authapp.api.paymentlist

import com.authapp.api.auth.ErrorInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentData(
    @Json(name = "response")
    val payments: List<Payment>?,
    val success: String,
    val error: ErrorInfo?
)
