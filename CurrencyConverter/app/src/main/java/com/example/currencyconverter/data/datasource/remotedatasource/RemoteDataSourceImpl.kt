package com.example.currencyconverter.data.datasource.remotedatasource

import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.CurrencyResponse
import com.example.currencyconverter.data.network.webservice.WebService
import com.example.currencyconverter.domain.contract.datasource.RemoteDataSource
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.CurrencyDto
import jakarta.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val webService: WebService
): RemoteDataSource {
     override suspend fun getAllCurrencies(): CurrencyResponse {
       return webService.getAllCurrencies()
    }

    override suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ):CurrencyConversionResponse{
        return webService.getCurrencyConversion(
            base=base,
            symbol = symbol
        )
    }


}