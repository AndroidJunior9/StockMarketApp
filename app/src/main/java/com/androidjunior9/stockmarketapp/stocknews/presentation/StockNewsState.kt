package com.androidjunior9.stockmarketapp.stocknews.presentation

import com.androidjunior9.stockmarketapp.stocknews.domain.model.Article

data class StockNewsState(
    val articles:List<Article> = emptyList(),
    val error:String? = null,
    val isLoading:Boolean = false,
)
