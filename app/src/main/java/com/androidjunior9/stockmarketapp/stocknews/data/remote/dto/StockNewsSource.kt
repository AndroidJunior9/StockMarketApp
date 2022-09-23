package com.androidjunior9.stockmarketapp.stocknews.data.remote.dto

import com.squareup.moshi.Json

data class StockNewsSource(
    @field:Json(name = "id") val id:String? = null,
    @field:Json(name = "name") val name:String? = null
)
