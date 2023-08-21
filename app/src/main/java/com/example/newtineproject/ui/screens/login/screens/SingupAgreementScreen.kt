package com.example.newtineproject.ui.screens.login.screens

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.SignupScreen
import com.example.newtineproject.ui.screens.login.components.SignupTopAppBar
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey

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

    var isLazyColumnVisible by rememberSaveable { mutableStateOf(true) }
    var allAgreementsChecked by rememberSaveable { mutableStateOf(false) }



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


                            itemsList(text = agreementList[index],agreementChecked)
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
                    navController.navigate(SignupScreen.Finish.route)
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
fun itemsList(text:String, agreementChecked: MutableState<Boolean>){
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


}

@Preview
@Composable
fun agreementPreview(){
    SignupAgreementScreen(navController = rememberNavController())
}