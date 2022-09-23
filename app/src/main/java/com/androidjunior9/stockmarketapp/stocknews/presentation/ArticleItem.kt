package com.androidjunior9.stockmarketapp.stocknews.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidjunior9.stockmarketapp.stocknews.domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ArticleItem(
    article:Article,
    modifier: Modifier,
){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ){
            val date = LocalDateTime.parse(article.publishedAt, DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'T'HH:mm:ssX"
            ))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "${date.dayOfMonth} ${date.month}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray
                )
                article.source.name?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

