package com.example.currencyconverter.data.datasource

import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.repo.RepoDataSoruce
import com.example.currencyconverter.domain.model.CurrencyDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RepoDataSourceImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
):RepoDataSoruce {
    override suspend fun getAllCurrencies(): Flow<ApiResult<CurrencyDto>> {
        return  remoteDataSource.getAllCurrencies()
    }
}