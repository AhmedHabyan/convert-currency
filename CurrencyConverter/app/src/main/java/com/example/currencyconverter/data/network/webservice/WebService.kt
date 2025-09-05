package com.example.currencyconverter.data.network.webservice

import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("latest")
    suspend fun getCurrencyConversion(
        @Query("access_key") apiKey:String= "4d2377652b476253aa9818b9863f8386",
        @Query("base") base:String,
        @Query("symbols") symbol:String
    ): CurrencyConversionResponse

    @GET("symbols")
    suspend fun getAllCurrencies(
        @Query("access_key") apiKey:String= "4d2377652b476253aa9818b9863f8386"
    ):CurrencyResponse

}