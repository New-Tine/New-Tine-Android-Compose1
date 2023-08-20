package com.example.newtineproject.ui.screens.newtech.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.newtech.NewTechProgress
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NewTechCurveDefaultColor

@Composable
fun ProgressBars(count: Int) {
    val currentState = coinNumbers[count]

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .fillMaxWidth(0.485f),
            painter = painterResource(id = R.drawable.newtech_curve_top),
            contentDescription = "newtech curve top",
            tint = if (currentState.secondBar) LightBlue else NewTechCurveDefaultColor
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Icon(
                    modifier = Modifier
                        .fillMaxHeight(0.35f),
                    painter = painterResource(id = R.drawable.newtech_curve_left),
                    contentDescription = "newtech curve left",
                    tint = if (currentState.firstBar) LightBlue else NewTechCurveDefaultColor
                )
            }
            Image(
                modifier = Modifier.fillMaxHeight(0.36f),
                painter = painterResource(id = currentState.coinImage),
                contentDescription = "three coins"
            )
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Icon(
                    modifier = Modifier.fillMaxHeight(0.35f),
                    painter = painterResource(id = R.drawable.newtech_curve_right),
                    contentDescription = "newtech curve right",
                    tint = if (currentState.thirdBar) LightBlue else NewTechCurveDefaultColor
                )
            }
        }
    }
}

val coinNumbers = listOf(
    NewTechProgress(
        firstBar = false,
        secondBar = false,
        thirdBar = false,
        coinImage = R.drawable.newtech_coin_0
    ),
    NewTechProgress(
        firstBar = true,
        secondBar = false,
        thirdBar = false,
        coinImage = R.drawable.newtech_coin_1
    ),
    NewTechProgress(
        firstBar = true,
        secondBar = true,
        thirdBar = false,
        coinImage = R.drawable.newtech_coin_2
    ),
    NewTechProgress(
        firstBar = true,
        secondBar = true,
        thirdBar = true,
        coinImage = R.drawable.newtech_coin_3
    ),
)