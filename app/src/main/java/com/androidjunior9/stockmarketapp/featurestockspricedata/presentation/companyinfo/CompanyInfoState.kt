package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companyinfo

import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo

data class CompanyInfoState(
    val WeeklyStockInfos:List<CompanyPriceInfo> = emptyList(),
    val isLoading:Boolean = false,
    val error:String? = null,
    val companyInfo: CompanyInfo? = null
)
