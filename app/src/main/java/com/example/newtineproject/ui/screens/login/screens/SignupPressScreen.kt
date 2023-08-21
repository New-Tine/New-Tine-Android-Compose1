package com.example.newtineproject.ui.screens.login.screens

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.newtineproject.ui.theme.LightGrey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPressScreen(navController: NavController){

    val imageList= listOf(
        R.drawable.press01,
        R.drawable.press02,
        R.drawable.press03,
        R.drawable.press04,
        R.drawable.press05,
        R.drawable.press06,
        R.drawable.press07,
        R.drawable.press08,
        R.drawable.press09,
        )

    val pressList= listOf<String>(
        "매일경제","YTN NEWS","KBS 뉴스","서울 경제","TV 조선",
        "MBC 뉴스","연합뉴스","전자신문","한국일보"
    )

    Scaffold (
        topBar = {
            SignupTopAppBar(navController = navController)
        },
        content={padding->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp, start = 20.dp, end = 20.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "구독하고 싶은",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold


                )
                Text(
                    text = "언론사를 선택해주세요",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold


                )
                Text(
                    text = "관심 언론사 3개 이상을 선택해 주세요",
                    fontSize = 15.sp,
                    color= Grey,
                    modifier = Modifier.padding(top=7.dp)

                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, bottom = 0.dp)

                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        Arrangement.Center
                    ){
                        for (i in 0..2){
                            pressList(
                                imageIndex = imageList[i],
                                pressName = pressList[i]
                            )
                        }

                    }

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        Arrangement.Center
                    ){
                        for (i in 3..5){
                            pressList(
                                imageIndex = imageList[i],
                                pressName = pressList[i]
                            )
                        }

                    }

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        Arrangement.Center
                    ){
                        for (i in 6..8){
                            pressList(
                                imageIndex = imageList[i],
                                pressName = pressList[i]
                            )
                        }

                    }


                }


                Spacer(modifier = Modifier.height(25.dp))

                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                    , modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "건너뛰기",
                        color = Grey
                    )

                }

                Button(onClick = {
                    navController.navigate(SignupScreen.Topics.route)
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

@Composable
fun pressList(imageIndex:Int,pressName:String){
    var checked by rememberSaveable {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier.padding(end=5.dp),
        Arrangement.Center
        ) {

            Image(painter = painterResource(id = imageIndex)
                , contentDescription ="${pressName}"
                ,Modifier.size(100.dp)
                )

            Row (
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentWidth()

            ){

                Text(text = "${pressName}",
                    Modifier.padding(end=5.dp),
                    fontSize = 15.sp
                    )
                
                Spacer(modifier = Modifier.padding(end=5.dp))

                Checkbox(checked = checked, onCheckedChange = {checked=it}
                    , modifier = Modifier.size(10.dp)
                    , colors = CheckboxDefaults.colors(
                        checkedColor = LightBlue,
                        uncheckedColor = LightGrey)

                )

//                IconButton(onClick = {
//                    checked= !checked
//
//                },
//                    modifier = Modifier.size(20.dp)
//
//                ) {
//                    if(checked){
//                        Icon(imageVector = Icons.Default.Check,
//                            contentDescription = null,
//                            modifier=Modifier.background(LightBlue))
//                    }
//                    else{
//                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
//
//                    }
//
//                }


            }


        }
}
@Preview
@Composable
fun SignupPressPreview(){
    SignupPressScreen(navController = rememberNavController())
}