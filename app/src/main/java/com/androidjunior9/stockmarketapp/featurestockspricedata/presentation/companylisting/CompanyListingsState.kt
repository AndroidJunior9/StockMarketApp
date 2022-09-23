package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companylisting

import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyListing

data class CompanyListingsState(
    val companies:List<CompanyListing> = emptyList(),
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false,
    val searchQuery:String = "",
    val error:String? = null
)
