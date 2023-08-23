package com.example.newtineproject.ui.screens.login.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.graphs.SignupScreen
import com.example.newtineproject.ui.screens.login.components.SignupTopAppBar
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupFinishScreen(navController: NavController) {
    Scaffold (
        topBar = {
            SignupTopAppBar(navController = navController)
        },
    ) {
        Column(
            modifier = Modifier.padding(10.dp) ,
            verticalArrangement = Arrangement.Center, // 수직으로 중앙 정렬
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                Arrangement.Center

            ){
                Image(painter = painterResource(id = R.drawable.signup_finish_3x), contentDescription =null,
                    modifier = Modifier.padding(start=70.dp)
                )


            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "회원 가입 완료!",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                    )
                Text(text = "뉴틴 회원이 되신 것을 환영해요",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                    )
                Text(text = "새로운 루틴! 뉴틴에 새로운 일상을 만들어가요!",
                    fontSize = 15.sp,
                    color = Grey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 10.dp)

                )

            }


            Button(onClick = {
                             navController.navigate(SignupScreen.Login.route)
            },
                colors = ButtonDefaults.buttonColors(LightBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(50.dp),

                shape = RoundedCornerShape(2.dp)


            ) {
                Text(text = "시작하기",
                    color = Color.White,
                    fontSize = 15.sp
                )


            }

        }

    }
}

@Preview
@Composable
fun preview(){
    SignupFinishScreen(navController = rememberNavController())
}