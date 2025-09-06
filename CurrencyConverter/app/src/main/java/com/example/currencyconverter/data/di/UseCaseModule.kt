package com.example.currencyconverter.data.di

import com.example.currencyconverter.data.usecase.ConverterUseCase
import com.example.currencyconverter.domain.contract.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCurrencyUseCase(repository:Repo):ConverterUseCase{
        return ConverterUseCase(repository)
    }
}