package com.androidjunior9.stockmarketapp.featurestockspricedata.data.mappers

import com.androidjunior9.stockmarketapp.featurestockspricedata.data.local.CompanyListingEntity
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.dto.CompanyInfoDto
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        exchange = exchange,
        symbol = symbol
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        exchange = exchange,
        symbol = symbol
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol?:"",
        name = name?:"",
        country = country?:"",
        industry = industry?:"",
        description = description?:""
    )
}