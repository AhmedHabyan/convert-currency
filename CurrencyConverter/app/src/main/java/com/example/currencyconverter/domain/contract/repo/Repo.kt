package com.example.currencyconverter.domain.contract.repo

import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.CurrencyDto
import kotlinx.coroutines.flow.Flow

interface Repo {

    suspend fun getAllCurrencies():Flow<ApiResult<CurrencyDto>>

    suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ):Flow<ApiResult<CurrencyConversionDto>>
}