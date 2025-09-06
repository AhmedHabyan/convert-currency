package com.example.currencyconverter.data.usecase

import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.CurrencyDto
import com.example.currencyconverter.domain.model.TransactionDto
import jakarta.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

class ConverterUseCase @Inject constructor(
    private val repo: Repo
) {
     suspend fun getAllCurrencies(): Flow<ApiResult<CurrencyDto>> {
        return  repo.getAllCurrencies()
    }

    suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ): Flow<ApiResult<CurrencyConversionDto>> {
        return repo.getCurrencyConversion(
            base=base,
            symbol = symbol
        )
    }

     suspend fun getAllTransactions(): Flow<ApiResult<List<TransactionDto>>> {
        return repo.getAllTransactions()
    }

     suspend fun insertTransaction(transactionDto: TransactionDto) {
        repo.insertTransaction(transactionDto)
    }
}