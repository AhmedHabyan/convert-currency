package com.example.currencyconverter.domain.model

import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionEntity

data class TransactionDto (
    val amountFrom:String?=null,
    val amountTo:String?=null,
    val status:String?=null
){
    fun toTransactionEntity():TransactionEntity{
        return TransactionEntity(
            amountFrom = amountFrom,
            amountTo = amountTo,
            status = status
        )
    }
}