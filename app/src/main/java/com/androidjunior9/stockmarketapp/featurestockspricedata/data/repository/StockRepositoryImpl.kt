package com.androidjunior9.stockmarketapp.featurestockspricedata.data.repository

import com.androidjunior9.stockmarketapp.featurestockspricedata.data.csv.CsvParser
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.local.StockDatabase
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.mappers.toCompanyInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.mappers.toCompanyListing
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.mappers.toCompanyListingEntity
import com.androidjunior9.stockmarketapp.featurestockspricedata.data.remote.StockApi
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyListing
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.repository.StockRepository
import com.androidjunior9.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CsvParser<CompanyListing>,
    private val companyPriceInfoParser: CsvParser<CompanyPriceInfo>
): StockRepository {
    private val dao = db.dao
    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow{
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response  = api.getListings()
                companyListingParser.parse(response.byteStream())
            }catch(e:IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }catch(e:HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings->
                dao.clearCompanyListing()
                dao.insertCompanyListing(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    dao.searchCompanyListing("")
                        .map{
                            it.toCompanyListing()
                        }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())
        }catch(e:IOException){
            e.printStackTrace()
            Resource.Error("Couldn't load company data")
        }catch(e:HttpException){
            e.printStackTrace()
            Resource.Error("Couldn't load company data")
        }
    }



    override suspend fun getWeeklyInfo(symbol: String): Resource<List<CompanyPriceInfo>> {
        return try{
            val response = api.getWeeklyInfo(symbol)
            val results = companyPriceInfoParser.parse(response.byteStream())
            Resource.Success(results)
        }catch(e:IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load data"
            )
        }catch(e:HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load data"
            )
        }
    }
}




















