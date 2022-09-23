package com.androidjunior9.stockmarketapp.stocknews.data.remote

import com.androidjunior9.stockmarketapp.stocknews.data.remote.dto.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StockNewsApi {

    @GET("/v2/top-headlines")
     suspend fun getStockNews(
        @Query("apikey") apikey:String = API_KEY,
        @Query("category") category:String = CATEGORY,
        @Query("language") language:String = LANGUAGE
     ):NewsApiResponse

     companion object{
         //declare your api key here
         const val API_KEY = ApiKey.API_KEY
         const val BASE_URL = "https://newsapi.org"
         const val CATEGORY = "business"
         const val LANGUAGE = "en"
     }
}