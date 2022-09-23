package com.androidjunior9.stockmarketapp.featurestockspricedata.domain.repository

import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyListing
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo
import com.androidjunior9.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun  getCompanyListing(
        fetchFromRemote:Boolean,
        query:String
    ): Flow<Resource<List<CompanyListing>>>
    suspend fun getWeeklyInfo(
        symbol:String
    ):Resource<List<CompanyPriceInfo>>
    suspend fun getCompanyInfo(symbol:String):Resource<CompanyInfo>
}