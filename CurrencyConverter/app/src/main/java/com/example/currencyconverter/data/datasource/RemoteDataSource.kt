package com.example.currencyconverter.data.datasource

import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.CurrencyResponse
import com.example.currencyconverter.data.network.webservice.WebService
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.model.CurrencyDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RemoteDataSource @Inject constructor(
    private val webService: WebService
) {
      suspend fun getAllCurrencies(): CurrencyResponse {
       return webService.getAllCurrencies()
    }

    suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ):CurrencyConversionResponse{
        return webService.getCurrencyConversion(
            base=base,
            symbol = symbol
        )
    }


}