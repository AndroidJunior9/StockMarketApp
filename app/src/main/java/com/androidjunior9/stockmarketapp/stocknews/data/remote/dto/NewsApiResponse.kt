package com.androidjunior9.stockmarketapp.stocknews.data.remote.dto

import com.squareup.moshi.Json

data class NewsApiResponse(
    @field:Json(name = "articles") val articles:List<StockNewsArticle>,
)
