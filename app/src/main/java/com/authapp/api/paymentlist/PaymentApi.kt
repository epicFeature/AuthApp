package com.authapp.api.paymentlist

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PaymentApi {

    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("api-test/payments")
    suspend fun getPaymentInfo(@Header("token") token: String): Response<PaymentData>
}