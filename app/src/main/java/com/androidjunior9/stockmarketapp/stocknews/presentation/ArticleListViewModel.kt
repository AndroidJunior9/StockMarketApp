package com.androidjunior9.stockmarketapp.stocknews.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidjunior9.stockmarketapp.stocknews.domain.repository.StockNewsRepository
import com.androidjunior9.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val repository: StockNewsRepository
):ViewModel() {
    var state by mutableStateOf(StockNewsState())
    init {
        getNews()
    }
    private fun getNews(){
        viewModelScope.launch {
            repository.getNews().collect{result ->
                when(result){
                    is Resource.Success -> {
                        result.data?.let{
                            state = state.copy(
                                articles = it,
                                error = null,
                            )

                        }

                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }
                }
            }
        }
    }
}