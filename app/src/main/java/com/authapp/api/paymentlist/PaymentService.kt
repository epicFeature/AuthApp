package com.authapp.api.paymentlist

import com.authapp.api.common.RetrofitInstance

object PaymentService {
    private val retrofit = RetrofitInstance.instance
    suspend fun getPaymentData(token: String) =
        retrofit.create(PaymentApi::class.java).getPaymentInfo(token)
}