package com.example.currencyconverter.data.network.di

import com.example.currencyconverter.data.network.webservice.WebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit):WebService{
        return retrofit.create(WebService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://data.fixer.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}