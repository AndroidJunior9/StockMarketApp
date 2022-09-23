package com.androidjunior9.stockmarketapp.stocknews.di

import com.androidjunior9.stockmarketapp.stocknews.data.remote.StockNewsApi
import com.androidjunior9.stockmarketapp.stocknews.data.repository.StockNewsRepositoryImpl
import com.androidjunior9.stockmarketapp.stocknews.domain.repository.StockNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStockNewsApi():StockNewsApi{
        return Retrofit.Builder()
            .baseUrl(StockNewsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockNewsRepository(api:StockNewsApi):StockNewsRepository{
        return StockNewsRepositoryImpl(api = api)
    }
}