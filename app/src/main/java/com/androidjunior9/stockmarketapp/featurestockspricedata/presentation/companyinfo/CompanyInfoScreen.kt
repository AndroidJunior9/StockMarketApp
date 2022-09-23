package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companyinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidjunior9.stockmarketapp.ui.theme.DarkBlue

@Composable
fun CompanyInfoScreen(
    viewModel: CompanyInfoViewModel = hiltViewModel(),

    ){
    val state = viewModel.state
    if(state.error == null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            state.companyInfo?.let { company->
                Text(
                    text = company.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = company.symbol,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Industry: ${company.industry}" ,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Country: ${company.country}" ,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = company.description,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                if(state.WeeklyStockInfos.isNotEmpty()){
                    Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Market Summary",
                            color = Color.White
                        )

                    Spacer(modifier = Modifier.height(100.dp))
                            LineChart(
                                infos = state.WeeklyStockInfos,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .align(CenterHorizontally)
                                    .padding(end = 8.dp)

                            )
                        }
                    }
                }
            }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ){
        if(state.isLoading){
            CircularProgressIndicator()
        }else if(state.error != null){
            Text(
                text = state.error,
                color = Color.Red
            )
        }
    }
}








