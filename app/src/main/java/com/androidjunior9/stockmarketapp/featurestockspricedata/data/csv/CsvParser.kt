package com.androidjunior9.stockmarketapp.featurestockspricedata.data.csv

import java.io.InputStream

interface CsvParser<T> {
    suspend fun parse(stream:InputStream):List<T>
}