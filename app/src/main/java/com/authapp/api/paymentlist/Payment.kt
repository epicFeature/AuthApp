package com.authapp.api.paymentlist

import com.squareup.moshi.JsonClass
import java.math.BigInteger

@JsonClass(generateAdapter = true)
data class Payment(
    val id: Int,
    val title: String,
    val amount: Any?,
    val created: BigInteger?
)
