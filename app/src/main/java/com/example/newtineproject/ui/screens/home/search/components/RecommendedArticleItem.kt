package com.example.newtineproject.ui.screens.home.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.domain.model.search.Recommendation

@Composable
fun RecommendedArticleItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {onItemClick(recommendation)}
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = recommendation.title,
                style = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(400)
                )
            )

            Text(
                text = recommendation.publication,
                style = LocalTextStyle.current.copy(
                    fontSize = 11.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight(400)
                )
            )
        }
    }

}