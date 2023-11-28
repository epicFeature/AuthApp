package com.authapp.api.paymentlist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentData(
    @Json(name = "response")
    val paymentList: List<Payment>
)
