package com.example.newtineproject.ui.screens.login.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.newtineproject.server.RetrofitClient
import com.example.newtineproject.server.Retrofit_SignupPost
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey
import retrofit2.Call
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupAgreementScreen(navController: NavController) {
    val agreementList= mutableListOf<String>()
    agreementList.add("[필수] 뉴틴 서비스 이용약관")
    agreementList.add("[필수] 개인정보 수집 및 이용약관")
    agreementList.add("[선택] 프로필 정보 추가 수집 동의")
    agreementList.add("[선택 광고성 정보 수신 동의")

    var checked by rememberSaveable {
        mutableStateOf(false)
    }

    val context= LocalContext.current

    var isLazyColumnVisible by rememberSaveable { mutableStateOf(true) }
    var allAgreementsChecked by rememberSaveable { mutableStateOf(false) }

    var checkedList = rememberSaveable {
        MutableList(agreementList.size){false}
    }

    Scaffold(
        topBar = {
            SignupTopAppBar(navController = navController)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 20.dp, end = 20.dp)
                .wrapContentHeight()
        ) {
            Text(
                text = "이용약관에",
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold


            )
            Text(
                text = "동의해주세요",
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold)

            Column(
                Modifier
                    .padding(10.dp)
                    .padding(top = 40.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Checkbox(checked = allAgreementsChecked, onCheckedChange = {checked->allAgreementsChecked=checked})
                    Text(text = "약관에 모두 동의합니다",
                        Modifier.padding(top=12.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { isLazyColumnVisible = !isLazyColumnVisible }) {
                        if(isLazyColumnVisible){
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription ="down")
                        }
                        else{
                            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "up")
                        }


                    }

                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(LightGrey)
                )

                LazyColumn(content = {
                    if(isLazyColumnVisible){
                        items(agreementList.size){
                                index ->
                            var agreementChecked= rememberSaveable{
                                mutableStateOf(allAgreementsChecked)
                            }

                            DisposableEffect(allAgreementsChecked) {
                                agreementChecked.value = allAgreementsChecked
                                onDispose { }
                            }

                            var checkBoolean=itemsList(text = agreementList[index],agreementChecked)
                            checkedList[index]=checkBoolean

                            DisposableEffect(checkedList) {
                                Log.d("checkedList", checkedList.toString())
                                onDispose { }
                            }
                        }


                    }

                })

                Text(text = " * 약관에 대한 상세 안내 또는 주의사항",
                    color = Grey,
                    modifier = Modifier.padding(start=5.dp)
                )


            }



            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    if (!(checkedList[0]&&checkedList[1])) {
                        Log.d("checkedlist",checkedList.toString())
                        showToast(context, "필수 동의 항목을 모두 클릭해 주세요!")
                    }
                    else{
                        //한꺼번에 retrofit으로 전송
                        // Retrofit을 사용하여 POST 요청 생성
                        val email= getUserInfo(context,"user_email").toString()
                        val password= getUserInfo(context,"user_password").toString()
                        val nickname= getUserInfo(context,"user_nickname").toString()
                        val name= getUserInfo(context,"user_name").toString()
                        Log.d("retrofit","${email} ${password} ${nickname} ${name}")

                        val postData = Retrofit_SignupPost(email, password,nickname,name)

                        val retrofitInterface = RetrofitClient().getRetrofitInterface()
                        retrofitInterface.SignupPost(postData)?.enqueue(object : retrofit2.Callback<Void>{
                            override fun onResponse(
                                call: Call<Void>,
                                response: Response<Void>
                            ) {
                                if (response.isSuccessful) {
                                    // HTTP 상태 코드 확인
                                    val statusCode = response.code()
                                    when (statusCode) {
                                        200 -> {
                                            // HTTP 상태 코드 200 (성공)인 경우
                                            Log.d("retrofit", "onResponse: Success")
                                            // result를 사용하여 추가 처리 수행

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
                                            Log.e("retrofit", "onResponse: Unexpected Status Code $statusCode")
                                            // 기타 예외 상황 처리
                                        }
                                    }
                                } else {
                                    Log.e("retrofit", "onResponse: Request Failed")
                                    // 응답이 성공하지 않은 경우에 대한 처리
                                }
                            }

                            override fun onFailure(call: Call<Void?>, t: Throwable) {
                                Log.e("retrofit", "onFailure: ${t.message}")
                                // 네트워크 요청 실패에 대한 처리
                            }
                        })

                        navController.navigate(SignupScreen.Finish.route)

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
}

@Composable
fun itemsList(text:String, agreementChecked: MutableState<Boolean>):Boolean{
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Checkbox(checked = agreementChecked.value, onCheckedChange = {checked->agreementChecked.value=checked})
        Text(text = "${text}",
            Modifier.padding(top=14.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "보기", color = Grey, fontSize = 13.sp)

        }

    }
    return agreementChecked.value


}

fun getUserInfo(context:Context,key:String):String?{
    val preference=context.getSharedPreferences("Signup",Context.MODE_PRIVATE)
    return preference.getString("${key}",null)
}

@Preview
@Composable
fun agreementPreview(){
    SignupAgreementScreen(navController = rememberNavController())
}