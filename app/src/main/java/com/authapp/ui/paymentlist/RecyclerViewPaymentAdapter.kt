package com.authapp.ui.paymentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.authapp.api.paymentlist.Payment
import com.authapp.databinding.RecyclerViewPaymentItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class RecyclerViewPaymentAdapter :
    RecyclerView.Adapter<RecyclerViewPaymentAdapter.PaymentViewHolder>() {

    private var _data: List<Payment> = emptyList()
    fun setData(data: List<Payment>) {
        _data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewPaymentItemBinding.inflate(inflater, parent, false)
        return PaymentViewHolder(binding)
    }

    override fun getItemCount(): Int = _data.count()

    override fun onBindViewHolder(
        holder: PaymentViewHolder,
        position: Int
    ) {
        val payment = _data[position]
        val context = holder.itemView.context

        with(holder.binding) {
            paymentName.text = payment.title
            amount.text = payment.amount.toString() //временная заклепка
            createdDate.text = timeProcessing(payment,isDate = true)
            createdTime.text=timeProcessing(payment,isDate = false)
        }
    }

    private fun timeProcessing(payment: Payment, isDate: Boolean): String {
        val timeInMillis = payment?.created
        var date = ""
        var time = ""
        if (timeInMillis != null) {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())

            date = dateFormat.format(timeInMillis)
            time = timeFormat.format(timeInMillis)
        }
        return when {
            isDate -> date
            !isDate -> time
            else -> ""
        }
    }


    class PaymentViewHolder(
        val binding: RecyclerViewPaymentItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}