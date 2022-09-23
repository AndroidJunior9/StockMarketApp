package com.androidjunior9.stockmarketapp.stocknews.data.repository

import com.androidjunior9.stockmarketapp.stocknews.data.mappers.toArticle
import com.androidjunior9.stockmarketapp.stocknews.data.remote.StockNewsApi
import com.androidjunior9.stockmarketapp.stocknews.domain.model.Article
import com.androidjunior9.stockmarketapp.stocknews.domain.repository.StockNewsRepository
import com.androidjunior9.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Singleton

@Singleton
class StockNewsRepositoryImpl (
    private val api:StockNewsApi
):StockNewsRepository {
    override suspend fun getNews(): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val articles = try{
                val response = api.getStockNews()
                response.articles
            }catch(e:IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load news"))
                null
            }catch(e:HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load news"))
                null
            }
            articles?.let { articlelist->
                emit(Resource.Success(
                    articlelist.map {
                        it.toArticle()
                    }
                ))
            }
            emit(Resource.Loading(false))
    }
}
}