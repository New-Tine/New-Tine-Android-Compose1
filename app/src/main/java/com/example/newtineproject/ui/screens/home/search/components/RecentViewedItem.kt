package com.example.newtineproject.ui.screens.home.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.domain.model.search.RecentViewedArticle

@Composable
fun RecentViewedItem(
    recentViewedArticle: RecentViewedArticle,
    onItemClick: (RecentViewedArticle) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable{onItemClick(recentViewedArticle)}
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = recentViewedArticle.thumbnail),
            contentDescription = "thumbnail Image",
            modifier = Modifier
                .width(110.dp)
                .height(75.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = recentViewedArticle.title,
            modifier = Modifier
                .width(110.dp),
            style = LocalTextStyle.current.copy(
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight(400)
            ),
            lineHeight = 15.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = recentViewedArticle.publication,
            modifier = Modifier
                .width(110.dp),
            style = LocalTextStyle.current.copy(
                fontSize = 10.sp,
                color = Color.Gray
            )
        )
    }
}