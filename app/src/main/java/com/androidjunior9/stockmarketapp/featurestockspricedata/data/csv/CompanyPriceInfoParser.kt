@file:Suppress("BlockingMethodInNonBlockingContext")

package com.androidjunior9.stockmarketapp.featurestockspricedata.data.csv

import com.androidjunior9.stockmarketapp.featurestockspricedata.data.mappers.toCompanyPriceInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.dto.CompanyPriceInfoDto
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class CompanyPriceInfoParser@Inject constructor(): CsvParser<CompanyPriceInfo> {
    override suspend fun parse(stream: InputStream): List<CompanyPriceInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull{ line->
                    val timestamp = line.getOrNull(0)?:return@mapNotNull null
                    val open  = line.getOrNull(1)?:return@mapNotNull null
                    val high = line.getOrNull(2)?:return@mapNotNull null
                    val low = line.getOrNull(3)?:return@mapNotNull  null
                    val close = line.getOrNull(4)?:return@mapNotNull null
                    val dto = CompanyPriceInfoDto(
                        close = close.toDouble(),
                        high = high.toDouble(),
                        low = low.toDouble(),
                        open = open.toDouble(),
                        timeStamp = timestamp
                    )
                    dto.toCompanyPriceInfo()

                }
                .sortedBy { it.date }
                .also {
                    csvReader.close()
                }
        }
    }

}