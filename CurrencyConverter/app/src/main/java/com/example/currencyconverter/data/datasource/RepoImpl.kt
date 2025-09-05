package com.example.currencyconverter.data.datasource


import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.CurrencyDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
):Repo {
    override suspend fun getAllCurrencies(): Flow<ApiResult<CurrencyDto>> {
        return  safeCallApi { remoteDataSource.getAllCurrencies().toCurrentDto()}
    }

    override suspend fun getCurrencyConversion(
        base:String,
        symbol:String
    ): Flow<ApiResult<CurrencyConversionDto>> {
        return safeCallApi{remoteDataSource.getCurrencyConversion(
            base=base,
            symbol = symbol
        ).toCurrencyConversionDto()}
    }
}