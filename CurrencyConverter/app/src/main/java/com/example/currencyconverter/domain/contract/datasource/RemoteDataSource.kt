package com.example.currencyconverter.domain.contract.datasource

import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.CurrencyResponse
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.CurrencyDto

interface RemoteDataSource {

    suspend fun getAllCurrencies(): CurrencyResponse

    suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ): CurrencyConversionResponse
}