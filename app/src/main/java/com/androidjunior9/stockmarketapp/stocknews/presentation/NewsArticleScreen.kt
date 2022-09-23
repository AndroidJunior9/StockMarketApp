package com.androidjunior9.stockmarketapp.stocknews.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidjunior9.stockmarketapp.ui.theme.DarkBlue

@Composable
fun NewsArticleScreen(
    viewModel: ArticleListViewModel = hiltViewModel()
){
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        Text(
            text = "News",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start)
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ){
            items(state.articles.size){ i->
                ArticleItem(
                    article = state.articles[i],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( horizontal = 16.dp)
                )
                if(i<state.articles.size){
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(DarkBlue)
                    )
                }

            }
        }
    }
}