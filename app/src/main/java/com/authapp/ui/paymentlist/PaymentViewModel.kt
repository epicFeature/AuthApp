package com.authapp.ui.paymentlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authapp.api.paymentlist.Payment
import com.authapp.api.paymentlist.PaymentService
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    var paymentLiveData = MutableLiveData<List<Payment>?>()
    fun makeApiCall(token: String) = viewModelScope.launch {
        try {
            PaymentService.getPaymentData(token)
                .also {
                    val result = it.body()!!
                    if (result.success.toBoolean()){
                        paymentLiveData.postValue(result.payments)
                    }else{
                        //тут по хорошему хэндлер ошибок
                    }
                }
        } catch (e: Exception) {
            Log.e("httpCall", e.message ?: "Unknown Error")
            paymentLiveData.postValue(null)
        }
    }
}