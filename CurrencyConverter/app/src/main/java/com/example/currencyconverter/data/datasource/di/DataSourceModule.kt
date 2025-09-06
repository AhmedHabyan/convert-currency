package com.example.currencyconverter.data.datasource.di

import com.example.currencyconverter.data.datasource.remotedatasource.RemoteDataSourceImpl
import com.example.currencyconverter.data.datasource.repository.RepoImpl
import com.example.currencyconverter.data.datasource.localdatasource.LocalDataSourceImpl
import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionDao
import com.example.currencyconverter.data.network.webservice.WebService
import com.example.currencyconverter.domain.contract.datasource.LocalDataSource
import com.example.currencyconverter.domain.contract.datasource.RemoteDataSource
import com.example.currencyconverter.domain.contract.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    @Singleton
    fun provideRepoDataSource(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ):Repo{
        return RepoImpl(
            remoteDataSource,
            localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(webService:WebService): RemoteDataSource {
        return RemoteDataSourceImpl(webService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(transactionDao: TransactionDao): LocalDataSource {
        return LocalDataSourceImpl(transactionDao)
    }
}