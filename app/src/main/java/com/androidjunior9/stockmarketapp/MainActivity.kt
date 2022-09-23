package com.androidjunior9.stockmarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.androidjunior9.stockmarketapp.navigation.BottomNavItem
import com.androidjunior9.stockmarketapp.navigation.BottomNavigationBar
import com.androidjunior9.stockmarketapp.navigation.Navigation
import com.androidjunior9.stockmarketapp.navigation.Routes
import com.androidjunior9.stockmarketapp.ui.theme.StockMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = Routes.COMPANY_LISTINGS_SCREEN,
                                    icon = Icons.Default.List
                                ),
                                BottomNavItem(
                                    name = "News",
                                    route = Routes.NEWS_ARTICLE_SCREEN,
                                    icon = Icons.Default.Newspaper
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Spacer(modifier = Modifier.padding(it))
                    Navigation(navController = navController)
                }
            }
        }
    }
}



