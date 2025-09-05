package com.example.currencyconverter.data.datasource.localdatasource

import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionDao
import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionDatabase
import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionEntity
import com.example.currencyconverter.domain.contract.datasource.LocalDataSource
import com.example.currencyconverter.domain.model.TransactionDto
import jakarta.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao
): LocalDataSource{

    override suspend fun insertTransaction(transactionDto: TransactionDto) {
        transactionDao.insertTransaction(transactionDto.toTransactionEntity())
    }

    override suspend fun getAllTransactions():List<TransactionEntity> {
        return transactionDao.getAllTransactions()
    }

}
