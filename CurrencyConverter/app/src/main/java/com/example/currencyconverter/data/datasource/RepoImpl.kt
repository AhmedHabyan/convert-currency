package com.example.currencyconverter.data.datasource

import android.util.Log
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
):Repo {
    override suspend fun getAllCurrencies(): Flow<ApiResult<CurrencyDto>> {
        Log.e("me","yes2")
        return  safeCallApi { remoteDataSource.getAllCurrencies().toCurrentDto()}
    }
}