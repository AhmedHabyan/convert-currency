package com.example.currencyconverter.data.datasource.localdatasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {



    @Query("select * from transactionTable")
    fun getAllTransactions(): List<TransactionEntity>

    @Insert
    fun insertTransaction(trasactionEntity: TransactionEntity)
}