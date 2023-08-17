@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newtineproject.ui.screens.home.habitsetting

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey
import com.example.newtineproject.ui.theme.NewTineProjectTheme
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HabitSettingScreen(navController: NavController) {

    var textInt = remember { mutableStateOf("  ")}
    val keyboardController = LocalSoftwareKeyboardController.current

    var firstChecked by rememberSaveable {
        mutableStateOf(false)
    }
    var secondChecked by rememberSaveable {
        mutableStateOf(false)
    }
    var thirdChecked by rememberSaveable {
        mutableStateOf(false)
    }

    val weekdays = arrayOf("월", "화", "수", "목", "금", "토", "일")

    //time picker
    val calender = Calendar.getInstance()
    val timeState = remember { mutableStateOf("") }

    val context = LocalContext.current
    val timePickerDialog = TimePickerDialog(
        context, { _, hour, minute ->
            timeState.value = "${hour}시: ${minute}분"
        },
        calender[Calendar.HOUR_OF_DAY],
        calender[Calendar.MINUTE],
        false
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("나의 습관 형성") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },

                actions = {
                    IconButton(onClick = { /*TODO*/ }) {

                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ){
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(top = 50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "1단계-읽을 시간",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 20.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )

                    LazyRow(
                        content = {
                            items(weekdays.size) { index ->
                                WeekdaysItem(day = weekdays[index])
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    )

                    Divider(
                        color = LightGrey,
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(5.dp)
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = "2단계- 알림 시간 설정",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 20.dp)

                    )

                    Button(
                        modifier = Modifier
                            .width(300.dp)
                            .wrapContentHeight()
                            .align(alignment = CenterHorizontally)
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(LightGrey),
                        onClick = {

                            timePickerDialog.show()
                        },
                    ) {
                        Text(
                            text = "시간 설정하러 가기",
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                    //Text(text = "${timeState.value} 으로 설정 되었습니다!")

                    Divider(
                        color = LightGrey,
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(5.dp)
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = "3단계- 루틴 목표 세우기",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Checkbox(
                                checked = firstChecked,
                                onCheckedChange = {
                                    firstChecked = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = LightBlue,
                                    uncheckedColor = Color.LightGray,
                                    checkmarkColor = Color.White
                                )
                            )

                            Text(
                                text = "오늘의 뉴테크 기사 읽기",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Checkbox(
                                checked = secondChecked,
                                onCheckedChange = {
                                    secondChecked = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = LightBlue,
                                    uncheckedColor = Color.LightGray,
                                    checkmarkColor = Color.White
                                )
                            )

                            Text(
                                text = "기사 ",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                            BasicTextField(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .border(1.dp, Color.LightGray)
                                    .size(width = 30.dp, height = 25.dp),
                                value = textInt.value,
                                onValueChange = {textValue->
                                    textInt.value = textValue
                                },
                                decorationBox = {innerTextField ->
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.white)
                                            )
                                    ){
                                        innerTextField()
                                    }
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = Color.Black
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                    }
                                ),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                ),
                                cursorBrush = SolidColor(Color.Transparent)
                            )
                            Text(
                                text = " 개 남기기",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Checkbox(
                                checked = thirdChecked,
                                onCheckedChange = {
                                    thirdChecked = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = LightBlue,
                                    uncheckedColor = Color.LightGray,
                                    checkmarkColor = Color.White
                                )
                            )

                            Text(
                                text = "의견 남기기",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()

                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Add, contentDescription = null)

                        }
                        Text(
                            text = "직접 입력",
                            color = Color.Black,
                            modifier = Modifier.padding(top = 10.dp)
                        )

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                    ){
                        Column {
                            Spacer(modifier = Modifier.height(100.dp))
                            Button(
                                onClick = {
                                    navController.popBackStack()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                shape = RoundedCornerShape(4.dp),
                                colors = ButtonDefaults.buttonColors(LightBlue),
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 7.dp, bottom = 7.dp),
                                    text = "습관 설정 완료",
                                    color = Color.White,
                                    style = LocalTextStyle.current.copy(
                                        fontSize = 17.sp
                                    )
                                )
                            }
                        }

                        Image(
                            painter = painterResource(id = R.drawable.calendar_habitsetting),
                            contentDescription = "calendar",
                            modifier = Modifier
                                .offset(x = 260.dp, y = 55.dp)
                                .size(100.dp)

                        )

                    }
                }
            }

        }
    )
}



@Composable
fun WeekdaysItem(day: String) {
    val context = LocalContext.current
    var isButtonClicked by remember {
        mutableStateOf(false)
    }

    Button(
        onClick = {
            isButtonClicked = !isButtonClicked
        },
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        ),
        modifier = Modifier
            .width(48.5.dp)
            .padding(0.dp)
            .border(
                1.dp,
                color = if (isButtonClicked) LightBlue else Color.Gray,
                RoundedCornerShape(4.dp)
            )
            .background(Color.White)
    ) {
        Text(
            text = day,
            fontSize = 14.sp,
            color = if(isButtonClicked) LightBlue else Color.Gray,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.padding(end = 5.dp))
}


@Preview(showBackground = true)
@Composable
fun HabitSettingPreview() {
    NewTineProjectTheme {
        HabitSettingScreen(navController = rememberNavController())
    }
}