package com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.dto

import com.squareup.moshi.Json

data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol:String?,
    @field:Json(name = "Name") val name:String?,
    @field:Json(name = "Country") val country:String?,
    @field:Json(name = "Industry") val industry:String?,
    @field:Json(name = "Description") val description:String?
)
