package com.example.currencyconverter.data.datasource.di

import com.example.currencyconverter.data.datasource.RemoteDataSource
import com.example.currencyconverter.data.datasource.RepoDataSourceImpl
import com.example.currencyconverter.data.network.webservice.WebService
import com.example.currencyconverter.domain.contract.repo.RepoDataSoruce
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
        remoteDataSource: RemoteDataSource
    ):RepoDataSoruce{
        return RepoDataSourceImpl(
            remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(webService:WebService):RemoteDataSource{
        return RemoteDataSource(webService)
    }
}