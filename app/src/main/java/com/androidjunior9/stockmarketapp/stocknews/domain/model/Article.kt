package com.androidjunior9.stockmarketapp.stocknews.domain.model

data class Article(
    val url:String?,
    val source: Source,
    val author:String?,
    val title:String,
    val description:String?,
    val publishedAt:String
)