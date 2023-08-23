package com.example.newtineproject.ui.screens.login.screens

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val textEmailState= remember { mutableStateOf("") }
    val textVerifyState= remember { mutableStateOf("") }
    var verifyState by rememberSaveable {
        mutableStateOf(false)
    }
    val context= LocalContext.current

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
                    placeholder = { Text(text = "이름", fontSize = 15.sp) },
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
                    androidx.compose.material3.TextField(value = textEmailState.value,
                        onValueChange = { textValue -> textEmailState.value = textValue },
                        modifier = Modifier
                            .background(
                                textInputGrey,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .height(50.dp)
                            .width(230.dp),
                        placeholder = { Text(text = "이메일 입력", fontSize = 15.sp) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = textInputGrey,
                            unfocusedContainerColor =textInputGrey
                        )
                    )


                    Button(
                        onClick = {
                                  //api 요청
                                  showToast(context,"인증 메일이 전송되었습니다! 인증을 완료해주세요!")
                        },
                        colors = ButtonDefaults.buttonColors(Color.DarkGray),
                        shape = RoundedCornerShape(30.dp),


                        ) {
                        Text(
                            text = "인증요청",
                            color = Color.White
                        )

                    }

                }


                Spacer(modifier = Modifier.height(20.dp))


                Row (
                    modifier=Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween
                ){
                    androidx.compose.material3.TextField(value = textVerifyState.value,
                        onValueChange = { textValue -> textVerifyState.value = textValue },
                        modifier = Modifier
                            .background(
                                textInputGrey,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .width(230.dp)
                            .height(50.dp),
                        placeholder = { Text(text = "인증번호 입력", fontSize = 15.sp) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = textInputGrey,
                            unfocusedContainerColor =textInputGrey
                        )
                    )

                    Button(
                        onClick = {
                            //우선은 클릭시 , 나중에 api 연결
                            verifyState=true
                        },
                        colors = ButtonDefaults.buttonColors(Color.DarkGray),
                        shape = RoundedCornerShape(30.dp),


                        ) {
                        Text(
                            text = "인증확인",
                            color = Color.White
                        )

                    }


                }


                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier=Modifier.fillMaxWidth()
                ) {
                    if(verifyState){
                        Text(text = "* 인증되었습니다", color = LightBlue, fontSize = 13.sp)
                        // 비밀번호 일치 여부를 확인하고 상태 업데이트
//                    LaunchedEffect(textPwState.value, textRePwState.value) {
//                        isPwSame = textPwState.value == textRePwState.value
//                    }

                    }


                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if(verifyState){
                            //sharedpreference name 저장
                            val preference=context.getSharedPreferences("Signup", Context.MODE_PRIVATE)
                            val editor=preference.edit()
                            editor.putString("user_name",textNameState.value)
                            editor.apply()

                            navController.navigate(SignupScreen.Press.route)

                        }
                        else{
                            showToast(context,"이메일 인증을 완료해 주세요!")
                        }

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