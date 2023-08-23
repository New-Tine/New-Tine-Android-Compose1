package com.example.newtineproject.ui.screens.login.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.graphs.SignupScreen
import com.example.newtineproject.ui.screens.login.components.SignupTopAppBar
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey
import com.example.newtineproject.ui.theme.textInputGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupProfileScreen(navController: NavController){
    val nickNameState=remember{ mutableStateOf("") }
    val context= LocalContext.current

    Scaffold (
        topBar = {
            SignupTopAppBar(navController = navController)
        },
        content={ padding->
            Column (modifier= Modifier
                .padding(20.dp),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=50.dp)

                ){
                    Text(text = "나만의 프로필을",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Text(text = "설정해주세요",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        Modifier.padding(start=30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)

                    ){
                        Icon(imageVector = Icons.Default.AccountCircle,
                            contentDescription = "photo",
                            Modifier.size(230.dp),
                            tint= Grey
                        )
                        Column(
                            Modifier.height(250.dp)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(Color.Transparent),
                                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                                containerColor = Color.Transparent,
                                contentColor = Color.Transparent,


                                ) {
                                Image(painter = painterResource(id = R.drawable.camera)
                                    , contentDescription = "add photo"
                                    ,Modifier.size(40.dp)
                                )
                            }

                        }


                    }

                }

                TextField(
                    value = nickNameState.value,
                    onValueChange = { textValue -> nickNameState.value = textValue },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(10.dp)
                        .background(
                            LightGrey,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    placeholder = { Text(text = "닉네임 입력") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = textInputGrey,
                        unfocusedContainerColor = textInputGrey
                    )

                    )

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    if(nickNameState.value.isBlank()){
                        showToast(context,"닉네임을 입력해주세요!")
                    }
                    else{
                        //sharedpreference id, pw 저장
                        val preference=context.getSharedPreferences("Signup", Context.MODE_PRIVATE)
                        val editor=preference.edit()
                        editor.putString("user_nickname",nickNameState.value)
                        editor.apply()
                        navController.navigate(SignupScreen.PhoneVerification.route)
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

@Preview(showBackground = true)
@Composable
fun SignupProfilePreview(){

}