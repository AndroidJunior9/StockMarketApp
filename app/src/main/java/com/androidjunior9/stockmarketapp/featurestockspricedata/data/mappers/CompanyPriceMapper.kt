package com.androidjunior9.stockmarketapp.featurestockspricedata.data.mappers

import com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.dto.CompanyPriceInfoDto
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun CompanyPriceInfoDto.toCompanyPriceInfo(): CompanyPriceInfo {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val localDateTime = LocalDate.parse(timeStamp,formatter)
    return CompanyPriceInfo(
        date = localDateTime,
        open = open,
        close = close,
        high = high,
        low = low
    )
}