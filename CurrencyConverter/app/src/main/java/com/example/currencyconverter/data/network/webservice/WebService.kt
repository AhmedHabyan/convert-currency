package com.example.currencyconverter.data.network.webservice

import com.example.currencyconverter.data.model.CurrencyResponse
import retrofit2.http.GET

interface WebService {
    @GET("latest")
    fun getAllCurrencies():CurrencyResponse
}