package com.example.currencyconverter.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.HistoryItemBinding
import com.example.currencyconverter.domain.model.TransactionDto
import com.example.currencyconverter.ui.Constants

class HistoryAdapter(
    private val transactions:MutableList<TransactionDto>
):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    class HistoryViewHolder(val historyBinding:HistoryItemBinding):RecyclerView.ViewHolder(historyBinding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(transaction:TransactionDto){
            historyBinding.tvTransactionDetails.text = "${transaction.amountFrom} = ${transaction.amountTo}"
            historyBinding.tvStatus.text = transaction.status
            if(transaction.status.equals(Constants.SUCCESS)){
                historyBinding.tvStatus.setTextColor(ContextCompat.getColor(historyBinding.root.context, R.color.green))
            }else{
                historyBinding.tvStatus.setTextColor(ContextCompat.getColor(historyBinding.root.context, R.color.red))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
       val binding = HistoryItemBinding.inflate(
           LayoutInflater.from(parent.context),parent,false
       )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
       val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount()= transactions.size

    fun setData(newTransactionList:List<TransactionDto>){
        transactions.addAll(newTransactionList)
        notifyDataSetChanged()
    }
}