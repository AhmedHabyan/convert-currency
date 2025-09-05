package com.example.currencyconverter.data.datasource


import com.example.currencyconverter.data.datasource.remotedatasource.RemoteDataSourceImpl
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.data.utils.safeCallApi
import com.example.currencyconverter.domain.contract.datasource.LocalDataSource
import com.example.currencyconverter.domain.contract.datasource.RemoteDataSource
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.CurrencyDto
import com.example.currencyconverter.domain.model.TransactionDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):Repo {
    override suspend fun getAllCurrencies(): Flow<ApiResult<CurrencyDto>> {
        return  safeCallApi { remoteDataSource.getAllCurrencies().toCurrencyDto()}
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

    override suspend fun getAllTransactions(): Flow<ApiResult<List<TransactionDto>>> {
        return safeCallApi {
            localDataSource.getAllTransactions().map {
                it.toTransactionDto()
            }
        }
    }

    override suspend fun insertTransaction(transactionDto: TransactionDto) {
        localDataSource.insertTransaction(transactionDto)
    }
}