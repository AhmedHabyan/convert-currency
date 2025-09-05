package com.example.currencyconverter.domain.contract.datasource

import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionEntity
import com.example.currencyconverter.domain.model.TransactionDto

interface LocalDataSource {

    suspend fun getAllTransactions():List<TransactionEntity>
    suspend fun insertTransaction(transactionDto: TransactionDto)
}