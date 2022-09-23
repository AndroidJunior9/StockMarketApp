package com.androidjunior9.stockmarketapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companyinfo.CompanyInfoScreen
import com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companylisting.CompanyListingsScreen
import com.androidjunior9.stockmarketapp.stocknews.presentation.NewsArticleScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.COMPANY_LISTINGS_SCREEN){
        composable(Routes.COMPANY_LISTINGS_SCREEN){
            CompanyListingsScreen(navController = navController)
        }
        composable(
            route = Routes.COMPANY_INFO_SCREEN + "?symbol={symbol}",
            arguments = listOf(
                navArgument(
                    name = "symbol"
                ){
                    type = NavType.StringType
                }
            )
        ){
            CompanyInfoScreen()
        }
        composable(Routes.NEWS_ARTICLE_SCREEN){
            NewsArticleScreen()
        }
    }
}