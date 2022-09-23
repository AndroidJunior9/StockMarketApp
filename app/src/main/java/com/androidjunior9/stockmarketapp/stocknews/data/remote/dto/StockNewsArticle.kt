package com.androidjunior9.stockmarketapp.stocknews.data.remote.dto

import com.squareup.moshi.Json

data class StockNewsArticle(
    @field:Json(name = "source") val source:StockNewsSource,
    @field:Json(name = "author") val author:String? = null,
    @field:Json(name = "title") val title:String,
    @field:Json(name = "description") val description:String? = null,
    @field:Json(name = "url") val url:String? = null,
    @field:Json(name = "publishedAt") val publishedAt:String
)
