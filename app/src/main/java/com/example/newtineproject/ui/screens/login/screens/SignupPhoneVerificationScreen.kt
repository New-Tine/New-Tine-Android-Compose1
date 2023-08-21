package com.example.newtineproject.ui.screens.login.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.SignupScreen
import com.example.newtineproject.ui.screens.login.components.SignupTopAppBar
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.textInputGrey


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupPhoneVerificationScreen(navController: NavController) {
    val textNameState= remember { mutableStateOf("") }
    val textPhoneState= remember { mutableStateOf("") }
    val textVerifyState= remember { mutableStateOf("") }

    androidx.compose.material.Scaffold(
        topBar = {
            SignupTopAppBar(navController = navController)
        },
        content = { padding ->
            Column(
                Modifier.padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "본인 확인을 위해",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = "인증을 진행해주세요",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                androidx.compose.material3.TextField(value = textNameState.value,
                    onValueChange = { textValue -> textNameState.value = textValue },
                    modifier = Modifier
                        .background(
                            textInputGrey,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .height(50.dp),
                    placeholder = { Text(text = "이름") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = textInputGrey,
                        unfocusedContainerColor =textInputGrey
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    Arrangement.SpaceBetween
                ) {
                    androidx.compose.material3.TextField(value = textPhoneState.value,
                        onValueChange = { textValue -> textPhoneState.value = textValue },
                        modifier = Modifier
                            .background(
                                textInputGrey,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .height(50.dp)
                            .width(230.dp),
                        placeholder = { Text(text = "휴대폰 번호(-제외)") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = textInputGrey,
                            unfocusedContainerColor =textInputGrey
                        )
                    )


                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.DarkGray),
                        shape = RoundedCornerShape(30.dp),


                        ) {
                        Text(
                            text = "인증받기",
                            color = Color.White
                        )

                    }

                }


                Spacer(modifier = Modifier.height(20.dp))





                androidx.compose.material3.TextField(value = textVerifyState.value,
                    onValueChange = { textValue -> textVerifyState.value = textValue },
                    modifier = Modifier
                        .background(
                            textInputGrey,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .height(50.dp),
                    placeholder = { Text(text = "인증번호 입력") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = textInputGrey,
                        unfocusedContainerColor =textInputGrey
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "* 인증되었습니다", color = LightBlue)


                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {

                        navController.navigate(SignupScreen.Press.route)
                    },
                    colors = ButtonDefaults.buttonColors(LightBlue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(50.dp),

                    shape = RoundedCornerShape(2.dp)


                ) {
                    Text(
                        text = "다음",
                        color = Color.White,
                        fontSize = 15.sp
                    )


                }


            }


        }
    )
}

@Preview
@Composable
fun verifyScreenPreview(){
    SignupPhoneVerificationScreen(navController = rememberNavController())
}