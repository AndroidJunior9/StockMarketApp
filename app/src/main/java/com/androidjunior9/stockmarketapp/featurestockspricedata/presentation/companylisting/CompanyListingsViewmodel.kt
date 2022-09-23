package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companylisting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.repository.StockRepository
import com.androidjunior9.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewmodel @Inject constructor(
    private val repository: StockRepository
):ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    private var searchJob:Job? = null

    init {
        getCompanyListings()
    }
    fun onEvent(event: CompanyListingsEvents){
        when(event){
            is CompanyListingsEvents.OnSearchQueryChange ->{
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }

            is CompanyListingsEvents.Refresh -> {
                state = state.copy(
                    isRefreshing = true
                )
                getCompanyListings(fetchFromRemote = true)
            }
        }
    }

    private fun getCompanyListings(
        query:String = state.searchQuery.lowercase(),
        fetchFromRemote:Boolean = false
    ){
        viewModelScope.launch{
            repository.getCompanyListing(fetchFromRemote, query).collect{ result->
                when(result){
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(
                                companies = it,
                                error = null,
                                isRefreshing = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message?:"An undetected error",
                            isLoading = false,
                            isRefreshing = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading,
                        )
                    }
                }
            }
        }
    }
}