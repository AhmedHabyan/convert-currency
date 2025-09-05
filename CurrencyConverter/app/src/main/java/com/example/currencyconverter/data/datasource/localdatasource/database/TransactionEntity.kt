package com.example.currencyconverter.data.datasource.localdatasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconverter.domain.model.TransactionDto


@Entity(tableName = "transactionTable")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    @ColumnInfo("amountFrom")
    val amountFrom:String?,

    @ColumnInfo("amountTo")
    val amountTo:String?,

    @ColumnInfo("status")
    val status:String?

    ){
    fun toTransactionDto():TransactionDto{
        return TransactionDto(
            amountFrom=amountFrom,
            amountTo=amountTo,
            status= status
        )
    }
}