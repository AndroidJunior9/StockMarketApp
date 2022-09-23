package com.androidjunior9.stockmarketapp.stocknews.data.mappers

import com.androidjunior9.stockmarketapp.stocknews.data.remote.dto.StockNewsArticle
import com.androidjunior9.stockmarketapp.stocknews.data.remote.dto.StockNewsSource
import com.androidjunior9.stockmarketapp.stocknews.domain.model.Article
import com.androidjunior9.stockmarketapp.stocknews.domain.model.Source

fun StockNewsArticle.toArticle(): Article {
    return Article(
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        publishedAt = publishedAt
    )
}

fun StockNewsSource.toSource():Source{
    return Source(
        id = id,
        name = name
    )
}