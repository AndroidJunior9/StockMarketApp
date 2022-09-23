package com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote

import com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey:String = API_KEY
    ):ResponseBody

    @GET("query?function=TIME_SERIES_WEEKLY&datatype=csv")
    suspend fun getWeeklyInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey:String = API_KEY
    ):ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto


    companion object {
        //declare your api key here
        const val API_KEY = ApiKey.API_KEY
        const val BASE_URL = "https://alphavantage.co"
    }
}