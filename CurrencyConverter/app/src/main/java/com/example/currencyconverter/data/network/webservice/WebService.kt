package com.example.currencyconverter.data.network.webservice

import com.example.currencyconverter.data.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("symbols")
    suspend fun getAllCurrencies(
        @Query("access_key") apiKey:String= "9965ba56a58a4d451edc48153ac3b8fd"
    ):CurrencyResponse

}