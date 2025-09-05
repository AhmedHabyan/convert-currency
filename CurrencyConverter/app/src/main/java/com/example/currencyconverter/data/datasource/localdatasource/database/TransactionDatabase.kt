package com.example.currencyconverter.data.datasource.localdatasource.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TransactionEntity::class], version = 1)
abstract class TransactionDatabase:RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}