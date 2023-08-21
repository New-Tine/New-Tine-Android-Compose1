package com.example.newtineproject.ui.screens.login.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch
import androidx.compose.material.SnackbarDuration
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text as Text


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupIdPwScreen(navController: NavController) {
    val textIdState= remember { mutableStateOf("") }
    val textPwState= remember { mutableStateOf("") }
    val textRePwState= remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState=scaffoldState,


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

                ){
                    Text(text = "아이디와 비밀번호를",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Text(text = "입력해주세요",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        Arrangement.SpaceAround
                    ){
                        androidx.compose.material3.TextField(
                            value = textIdState.value,
                            onValueChange = { textValue -> textIdState.value = textValue },
                            modifier = Modifier
                                .background(
                                    textInputGrey,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .height(50.dp)
                                .width(230.dp),
                            placeholder = { Text(text = "아이디(4자리 이상)") },
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
                            Text(text = "중복확인",
                                color= Color.White
                            )

                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "* 사용 가능한 아이디입니다", color = LightBlue)


                }

                Spacer(modifier = Modifier.height(20.dp))


                Column(
                ) {
                    androidx.compose.material3.TextField(value = textPwState.value, onValueChange = { textValue->textPwState.value=textValue},
                        modifier = Modifier
                            .background(
                                textInputGrey,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .fillMaxWidth()
                            .height(50.dp)
                        ,
                        placeholder = { Text(text = "비밀번호 설정(8자리 이상)") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = textInputGrey,
                            unfocusedContainerColor =textInputGrey
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    androidx.compose.material3.TextField(value = textRePwState.value, onValueChange = { textValue->textRePwState.value=textValue},
                        modifier = Modifier
                            .background(
                                textInputGrey,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .fillMaxWidth()
                            .height(50.dp)
                        ,
                        placeholder = { Text(text = "비밀번호 재입력") }
                        ,colors = TextFieldDefaults.colors(
                            focusedContainerColor = textInputGrey,
                            unfocusedContainerColor =textInputGrey
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "* 입력한 비밀번호와 일치합니다", color = LightBlue)

                }



                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    navController.navigate(SignupScreen.Profile.route)

//                    var isBlankExists=false
//                    var isPwSame=false
//                    if(textIdState.value.isBlank()|| textPwState.value.isBlank()||textRePwState.value.isBlank()){
//                        isBlankExists=true
//                    }
//
//                    if(textPwState==textRePwState){
//                        isPwSame=true
//                    }
//
//                    if(!isBlankExists&& isPwSame){
//                        val editor=sharedPreferences.edit()
//                        editor.putString("email",textIdState.value)
//                        editor.putString("password",textPwState.value)
//                        editor.apply()
//                        navController.navigate(SignupScreen.Profile.route)

//                    }
//                    else{
//
//                        coroutineScope.launch {
//                            scaffoldState.snackbarHostState.showSnackbar(
//                                message = "이메일과 비밀번호를 다시 확인해주세요!",
//                                duration = SnackbarDuration.Short
//                            )
//                        }
//
//                    }

                },
                    colors = ButtonDefaults.buttonColors(LightBlue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(50.dp),

                    shape = RoundedCornerShape(2.dp)


                ) {
                    Text(text = "다음",
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

fun IdPwpreview(){
}