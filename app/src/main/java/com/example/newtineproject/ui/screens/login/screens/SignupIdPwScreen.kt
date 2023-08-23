package com.example.newtineproject.ui.screens.login.screens

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.newtineproject.ui.screens.login.server.RetrofitClient
import com.example.newtineproject.ui.screens.login.server.Retrofit_SignupPost
import retrofit2.Call
import retrofit2.Response
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
    var isPwSame by rememberSaveable {
        mutableStateOf(false)
    }

    var isValidEmail by rememberSaveable {
        mutableStateOf(false)
    }



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
                    Text(text = "이메일과 비밀번호를",
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

                            placeholder = { Text(text = "이메일") },
                     
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = textInputGrey,
                                unfocusedContainerColor =textInputGrey
                            )
                        )


                        Button(
                            onClick = {
                                isValidEmail=true
                            },
                            colors = ButtonDefaults.buttonColors(Color.DarkGray),
                            shape = RoundedCornerShape(30.dp),


                            ) {
                            Text(text = "중복확인",
                                color= Color.White
                            )

                        }

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if(isValidEmail){
                        Text(text = "* 사용 가능한 이메일입니다", color = LightBlue)
                        // 비밀번호 일치 여부를 확인하고 상태 업데이트
                        LaunchedEffect(textPwState.value, textRePwState.value) {
                            isPwSame = textPwState.value == textRePwState.value
                        }

                    }


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
                        placeholder = { Text(
                            text = "비밀번호 설정(8자리 이상)",
                            fontSize = 15.sp
                        ) },
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
                        placeholder = { Text(
                            text = "비밀번호 재입력",
                            fontSize = 15.sp
                        ) }
                        ,colors = TextFieldDefaults.colors(
                            focusedContainerColor = textInputGrey,
                            unfocusedContainerColor =textInputGrey
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    if(isPwSame){
                        Text(text = "* 입력한 비밀번호와 일치합니다", color = LightBlue)

                    }



                }



                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {

                    var isBlankExists=false
                    if(textIdState.value.isBlank()|| textPwState.value.isBlank()||textRePwState.value.isBlank()){
                        isBlankExists=true
                    }

//                    if(textPwState.value==textRePwState.value){
//                        isPwSame=true
//                    }

                    if(!isBlankExists&& isPwSame){

                        val email = textIdState.value
                        val password = textPwState.value
                        val isBlankExists = email.isBlank() || password.isBlank()

                        //sharedpreference id, pw 저장
                        val preference=context.getSharedPreferences("Signup",Context.MODE_PRIVATE)
                        val editor=preference.edit()
                        editor.putString("user_email",textIdState.value)
                        editor.putString("user_password",textPwState.value)
                        editor.apply()

                        // Retrofit을 사용하여 POST 요청 생성
//                        val postData = Retrofit_SignupPost(email, password)
//                        val ACCESS_TOKEN = "your_access_token_here"
//                        val bearerToken = "Bearer $ACCESS_TOKEN"
//                        val retrofitInterface = RetrofitClient().getRetrofitInterface()
//
//                        retrofitInterface.SignupPost(postData)?.enqueue(object : retrofit2.Callback<Void>{
//                            override fun onResponse(
//                                call: Call<Void>,
//                                response: Response<Void>
//                            ) {
//                                if (response.isSuccessful) {
//                                    // HTTP 상태 코드 확인
//                                    val statusCode = response.code()
//                                    when (statusCode) {
//                                        200 -> {
//                                            // HTTP 상태 코드 200 (성공)인 경우
//                                            Log.d("retrofit", "onResponse: Success")
//                                            // result를 사용하여 추가 처리 수행
//                                        }
//                                        400 -> {
//                                            // HTTP 상태 코드 400 (Bad Request)인 경우
//                                            Log.e("retrofit", "onResponse: Bad Request")
//                                            // 에러 처리 로직
//                                        }
//                                        401 -> {
//                                            // HTTP 상태 코드 401 (Unauthorized)인 경우
//                                            Log.e("retrofit", "onResponse: Unauthorized")
//                                            // 에러 처리 로직
//                                        }
//                                        // 다른 HTTP 상태 코드에 대한 처리 추가
//                                        else -> {
//                                            Log.e("retrofit", "onResponse: Unexpected Status Code $statusCode")
//                                            // 기타 예외 상황 처리
//                                        }
//                                    }
//                                } else {
//                                    Log.e("retrofit", "onResponse: Request Failed")
//                                    // 응답이 성공하지 않은 경우에 대한 처리
//                                }
//                            }
//
//                            override fun onFailure(call: Call<Void?>, t: Throwable) {
//                                Log.e("retrofit", "onFailure: ${t.message}")
//                                // 네트워크 요청 실패에 대한 처리
//                            }
//                        })
                        navController.navigate(SignupScreen.Profile.route)

                    }
                    else{
                        showToast(context,"이메일과 비밀번호를 확인해주세요!")


                    }

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
