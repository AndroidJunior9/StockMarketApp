package com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model

import java.time.LocalDate

data class CompanyPriceInfo(
    val date: LocalDate,
    val close:Double,
    val open:Double,
    val high:Double,
    val low:Double
)
