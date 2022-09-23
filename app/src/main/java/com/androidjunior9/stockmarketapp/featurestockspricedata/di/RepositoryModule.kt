package com.androidjunior9.stockmarketapp.featurestockspricedata.di

import com.androidjunior9.stockmarketapp.featurestockspricedata.data.csv.CompanyListingParser
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.csv.CompanyPriceInfoParser
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.csv.CsvParser
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.repository.StockRepositoryImpl
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyListing
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingParser: CompanyListingParser
    ): CsvParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

    @Binds
    @Singleton
    abstract fun bindCompanyPriceParser(
        companyPriceInfoParser: CompanyPriceInfoParser
    ): CsvParser<CompanyPriceInfo>
}