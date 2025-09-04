package com.example.currencyconverter.domain.contract.repo

import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.domain.model.CurrencyDto
import kotlinx.coroutines.flow.Flow

interface RepoDataSoruce {

    suspend fun getAllCurrencies():Flow<ApiResult<CurrencyDto>>
}