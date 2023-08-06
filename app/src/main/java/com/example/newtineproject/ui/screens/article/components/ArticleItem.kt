package com.example.newtineproject.ui.screens.article.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.domain.model.Article

@Composable
fun ArticleItem (
    article: Article,
    onItemClick: (Article) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(article) }
            .padding(horizontal = 17.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start

            ) {
                Text(
                    text = article.title,
                    style = LocalTextStyle.current.copy(
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .width(203.dp)
                        .height(52.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = article.publication,
                        style = LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        //case("분 전","시간 전") 구분 필요
                        text = article.timePassed.toString() + "시간 전",
                        style = LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    )
                }
            }
            Image(
                painter = painterResource(id = article.thumbnail),
                contentDescription = "thumbnail Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(72.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
        Divider()
    }


}
