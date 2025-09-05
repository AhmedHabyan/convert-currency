package com.example.currencyconverter.data.datasource

import android.util.Log
import com.example.currencyconverter.data.model.CurrencyResponse
import com.example.currencyconverter.data.network.webservice.WebService
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RemoteDataSource @Inject constructor(
    private val webService: WebService
) {
      suspend fun getAllCurrencies(): CurrencyResponse {
          Log.e("me","yes1")
       return webService.getAllCurrencies()
    }

    suspend fun getCurrencyConversion(): CurrencyResponse {
        Log.e("me","yes1")
        return webService.getAllCurrencies()
    }
}