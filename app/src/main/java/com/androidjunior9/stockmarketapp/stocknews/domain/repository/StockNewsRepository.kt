package com.androidjunior9.stockmarketapp.stocknews.domain.repository

import com.androidjunior9.stockmarketapp.stocknews.domain.model.Article
import com.androidjunior9.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockNewsRepository {
    suspend fun getNews(): Flow<Resource<List<Article>>>
}