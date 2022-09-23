package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companylisting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androidjunior9.stockmarketapp.navigation.Routes
import com.androidjunior9.stockmarketapp.ui.theme.DarkBlue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CompanyListingsScreen(
    viewModel: CompanyListingsViewmodel = hiltViewModel(),
    navController: NavController
){
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state
    if(state.error==null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DarkBlue),
        ){
            TextField(
                value = viewModel.state.searchQuery,
                onValueChange = {
                    viewModel.onEvent(CompanyListingsEvents.OnSearchQueryChange(it))
                                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text("Search...")
            },
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    textColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
        )
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(CompanyListingsEvents.Refresh)
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 90.dp)
                ){
                    items(state.companies.size){i->
                        val company = state.companies[i]
                        CompanyItem(
                            company = company,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Routes.COMPANY_INFO_SCREEN +
                                                "?symbol=${company.symbol}"
                                    )
                                }
                                .padding(16.dp)
                        )
                        if(i<state.companies.size){
                            Divider(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
        if (state.isLoading && !state.isRefreshing) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = Color.Red
            )
        }
    }
}











