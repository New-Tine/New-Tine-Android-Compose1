package com.example.newtineproject.ui.screens.login.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.graphs.SignupScreen
import com.example.newtineproject.ui.screens.login.KaKaoAuthViewModel
import com.example.newtineproject.ui.screens.login.server.RetrofitClient
import com.example.newtineproject.ui.screens.login.server.Retrofit_LoginPost
import com.example.newtineproject.ui.screens.login.server.Retrofit_LoginResult
import com.example.newtineproject.ui.screens.login.server.Retrofit_SignupPost
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey
import com.example.newtineproject.ui.theme.textInputGrey
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController = rememberNavController()) {
    val viewModel=viewModel<KaKaoAuthViewModel>()
    val context= LocalContext.current

    val textIdState= remember { mutableStateOf("") }
    val textPwState= remember { mutableStateOf("") }
    var loginState by rememberSaveable {
        mutableStateOf(false)
    }
    val coroutineScope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()

    Scaffold(
    ) {
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
                androidx.compose.material3.TextField(
                    value = textIdState.value,
                    onValueChange = {textValue->textIdState.value=textValue},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            textInputGrey,
                            shape = RoundedCornerShape(10.dp)
                        )
                    ,
                    placeholder = {Text(text="이메일")},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = textInputGrey,
                        unfocusedContainerColor =textInputGrey
                    )

                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                )

                androidx.compose.material3.TextField(
                    value = textPwState.value,
                    onValueChange = {textValue->textPwState.value=textValue},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            textInputGrey,
                            shape = RoundedCornerShape(10.dp)
                        )
                    ,
                    placeholder = {Text(text="비밀번호")},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = textInputGrey,
                        unfocusedContainerColor =textInputGrey
                    )

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

                Button(
                    onClick =
                    {
                        //여기서 id와 pw가 같으면
                        var isBlankExists = false
                        var isPwSame = false
                        if (textIdState.value.isBlank() || textPwState.value.isBlank()) {
                            isBlankExists = true
                        }

                        var email = textIdState.value
                        var password = textPwState.value
                        isBlankExists = email.isBlank() || password.isBlank()

                        if (!isBlankExists) {
                            // Retrofit을 사용하여 POST 요청 생성
                            val postData = Retrofit_LoginPost(email, password)
                            val retrofitInterface = RetrofitClient().getRetrofitInterface()


                            retrofitInterface.LoginPost(postData)?.enqueue(object : retrofit2.Callback<Retrofit_LoginResult?> {
                                override fun onResponse(
                                    call: Call<Retrofit_LoginResult?>,
                                    response: Response<Retrofit_LoginResult?>
                                ) {
                                    if (response.isSuccessful) {
                                        // HTTP 상태 코드 확인
                                        val statusCode = response.code()
                                        when (statusCode) {
                                            200 -> {
                                                // HTTP 상태 코드 200 (성공)인 경우
                                                val result = response.body()
                                                if (result != null) {
                                                     //result를 사용하여 추가 처리 수행
                                                    Log.d("retrofit", "onResponse: Success")
                                                    Log.d(
                                                        "retrofit",
                                                        "userId: ${result.userId}"
                                                    )
                                                    Log.d("retrofit", "email: ${result.email}")
                                                    Log.d(
                                                        "retrofit",
                                                        "accessToken: ${result.accessToken}"
                                                    )
                                                    Log.d(
                                                        "retrofit",
                                                        "refreshToken: ${result.refreshToken}"
                                                    )

                                                    saveUserToken(context, result?.accessToken.toString() ?: "null")

                                                    //user email 저장->my page 닉네임
                                                    val preference=context.getSharedPreferences("User_info",Context.MODE_PRIVATE)
                                                    val editor=preference.edit()
                                                    editor.putString("user_email",result.email)
                                                    editor.apply()

                                                    Log.d("gerUserToken", getUserToken(context).toString())

                                                    showToast(context,"로그인이 완료되었습니다!")

                                                    navController.navigate(SignupScreen.GotoMain.route)


                                                } else {
                                                    // 서버에서 유효한 응답을 받지 못한 경우
                                                    Log.e(
                                                        "retrofit",
                                                        "onResponse: Response body is null"
                                                    )

                                                    showToast(context,"이메일과 비밀번호를 확인해주세요!")


                                                }
                                            }


                                            400 -> {
                                                // HTTP 상태 코드 400 (Bad Request)인 경우
                                                Log.e("retrofit", "onResponse: Bad Request")
                                                // 에러 처리 로직
                                            }

                                            401 -> {
                                                // HTTP 상태 코드 401 (Unauthorized)인 경우
                                                Log.e("retrofit", "onResponse: Unauthorized")
                                                // 에러 처리 로직
                                            }
                                            // 다른 HTTP 상태 코드에 대한 처리 추가
                                            else -> {
                                                Log.e(
                                                    "retrofit",
                                                    "onResponse: Unexpected Status Code $statusCode"
                                                )
                                                // 기타 예외 상황 처리
                                            }
                                        }
                                    } else {
                                        Log.e("retrofit", "onResponse: Request Failed")
                                        // 응답이 성공하지 않은 경우에 대한 처리

                                        showToast(context,"로그인이 실패하였습니다! 회원가입을 먼저 진행해 주세요!")

                                    }
                                }

                                override fun onFailure(
                                    call: Call<Retrofit_LoginResult?>,
                                    t: Throwable
                                ) {
                                    Log.e("retrofit", "onFailure: ${t.message}")
                                    // 네트워크 요청 실패에 대한 처리

                                    showToast(context,"로그인이 실패하였습니다! 회원가입을 먼저 진행해 주세요!")



                                }
                            })

                        }
                        else{
                            showToast(context,"이메일과 비밀번호를 모두 입력해 주세요!")

                        }





                    },
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

                    TextButton(
                        onClick = {
                           navController.navigate(SignupScreen.IdPw.route)
                        }) {
                        Text(text = "회원가입",
                            color = Grey
                        )

                    }

                }

                Row (
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 50.dp)
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
                , modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(painter = painterResource(id = R.drawable.kakao_login_large_wide),
                    contentDescription ="kakao login",
                )

            }


        }

    }




}
fun saveUserToken(context: Context,token:String){
    val preference=context.getSharedPreferences("User_info",Context.MODE_PRIVATE)
    val editor=preference.edit()
    editor.putString("user_token",token)
    editor.apply()
}

fun getUserToken(context:Context):String?{
    val preference=context.getSharedPreferences("User_info",Context.MODE_PRIVATE)
    return preference.getString("user_token",null)
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}