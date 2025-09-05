package com.example.currencyconverter.data.datasource

import com.example.currencyconverter.data.network.webservice.WebService
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.repo.RepoDataSoruce
import com.example.currencyconverter.domain.model.CurrencyDto
import kotlinx.coroutines.flow.Flow

class RemoteDataSource @Inject constructor(
    private val webService: WebService
) {
      suspend fun getAllCurrencies(): CurrencyResponse {
          Log.e("me","yes1")
       return webService.getAllCurrencies()
    }



    suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ): Flow<ApiResult<CurrencyConversionDto>> {
        return safeCallApi{webService.getCurrencyConversion(
           base=base,
            symbol = symbol
        ).toCurrencyConversionDto()}
    }
}