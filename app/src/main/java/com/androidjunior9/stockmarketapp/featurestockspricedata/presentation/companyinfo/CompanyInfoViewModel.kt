package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companyinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.repository.StockRepository
import com.androidjunior9.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
):ViewModel() {
    var state by mutableStateOf(CompanyInfoState())

    init{
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol")?:return@launch
            state = state.copy(isLoading = true)
            val companyInfoResult = async { repository.getCompanyInfo(symbol) }
            val weeklyInfoResult = async {repository.getWeeklyInfo(symbol)}
            when(val result = companyInfoResult.await()){
                is Resource.Success -> {
                    state = state.copy(
                        companyInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                else -> Unit
            }


            when(val result = weeklyInfoResult.await()){
                is Resource.Success -> {
                    state = state.copy(
                        WeeklyStockInfos = result.data?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                    )
                }

                else -> Unit
            }
        }
    }

    fun roundToDecimal(number:Float):Float{
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toFloat()
    }
}