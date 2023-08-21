package com.example.newtineproject.ui.screens.login

import androidx.activity.viewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.R
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val textIdState= remember { mutableStateOf("") }
    val textPwState= remember { mutableStateOf("") }
    var loginState by rememberSaveable {
        mutableStateOf(false)
    }
    val viewModel=viewModel<KaKaoAuthViewModel>()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.newtine_logo),
                contentDescription = "newtine logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 4.dp)
            )
        }

        Column {
            TextField(
                value = textIdState.value,
                onValueChange = {textValue->textIdState.value=textValue},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        LightGrey,
                        shape = RoundedCornerShape(10.dp)
                    )
                ,
                placeholder = {Text(text="아이디")},
                colors= TextFieldDefaults.colors(LightGrey)


                )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
            )

            TextField(
                value = textPwState.value,
                onValueChange = {textValue->textPwState.value=textValue},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        LightGrey,
                        shape = RoundedCornerShape(10.dp)
                    )
                ,
                placeholder = {Text(text="비밀번호")},
                colors= TextFieldDefaults.colors(LightGrey),


            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp)

            ){
                IconButton(onClick = {
                    loginState= !loginState

                },
                    modifier = Modifier.size(30.dp)

                ) {
                    if(loginState){
                        Icon(imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier=Modifier.background(LightBlue))
                    }
                    else{
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)

                    }

                }
                Text(text = "로그인 상태 유지",
                    color = Grey,
                    fontSize =15.sp
                )

            }

            Button(onClick = { /*TODO*/ },
                colors= ButtonDefaults.buttonColors(LightBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(5.dp)


                ) {
                Text(text = "로그인 하기",
                    fontSize =15.sp,
                )

            }

            Row (
                modifier= Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                Arrangement.Center){
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "아이디 찾기",
                        color = Grey
                        )

                }


                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "비밀번호 찾기",
                        color = Grey
                    )

                }

                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "회원가입",
                        color = Grey
                    )

                }


            }

            Row (
                Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top=50.dp)
                ,
                Arrangement.Center

            ){
                Text(text = "기존 sns 계정으로 로그인"
                    , fontSize = 15.sp
                )

            }


        }

        Button(onClick = {viewModel.handleKakaoLogin() }
            , colors = ButtonDefaults.buttonColors(Color.Transparent)
            , modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(painter = painterResource(id = R.drawable.kakao_login_large_wide),
                contentDescription ="kakao login",
                )

    }




}
}
