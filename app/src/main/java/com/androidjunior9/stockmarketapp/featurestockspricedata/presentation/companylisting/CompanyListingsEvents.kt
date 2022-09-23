package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companylisting

sealed class CompanyListingsEvents{
    object Refresh: CompanyListingsEvents()
    data class OnSearchQueryChange(val query:String): CompanyListingsEvents()
}
