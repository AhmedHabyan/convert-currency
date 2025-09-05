package com.example.corefeatures.task_room.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionDao
import com.example.currencyconverter.data.datasource.localdatasource.database.TransactionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TransactionRoomModule {

    @Provides
    @Singleton
    fun provideTransactionDatabase(
        @ApplicationContext context:Context
    ): TransactionDatabase {



        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java, "transaction_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(transactionDatabase: TransactionDatabase):TransactionDao{
        return transactionDatabase.transactionDao()
    }
}