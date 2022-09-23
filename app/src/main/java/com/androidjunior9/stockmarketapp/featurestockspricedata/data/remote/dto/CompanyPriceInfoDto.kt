package com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.dto

data class CompanyPriceInfoDto(
    val timeStamp:String,
    val close:Double,
    val open:Double,
    val high:Double,
    val low:Double
)
