@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newtineproject.ui.habbit_making

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGrey
import com.example.newtineproject.ui.theme.NewTineProjectTheme
import java.util.Calendar

class Habbit_Making_Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewTineProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    habbit_making_Screen()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun habbit_making_Screen(){

    val weekdays= arrayOf("월","화","수","목","금","토","일")
    val goal_list= arrayOf("하루에 읽을 기사 갯수 정하기","하루 뉴틴에 머무는 시간 정하기"
        ,"하루 기사에 의견 남기기 갯수 정하기","하루 실시간 라이브 토론 참여하기"
    )

    //time picker
    val calender= Calendar.getInstance()
    val timeState=remember{mutableStateOf("")}

    val context=LocalContext.current
    val timePickerDialog=TimePickerDialog(
        context,{
            _,hour,minute->
            timeState.value="${hour}: ${minute}"
        },
        calender[Calendar.HOUR_OF_DAY],
        calender[Calendar.MINUTE],
        false
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("나의 습관 형성") } ,
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)

                    }
                },

                actions={
                    IconButton(onClick = { /*TODO*/ }) {
                        
                    }
                }

            )
        },

        content = {
            padding ->
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 50.dp)
                    .fillMaxSize()

            ) {
                Text(text = "1단계-읽을 시간",
                    fontSize=15.sp,
                    modifier= Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 20.dp)

                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))


                LazyRow(
                    content = {
                        items(weekdays.size){index->
                            weekdaysItem(day = weekdays[index])
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ,
                    horizontalArrangement = Arrangement.Center
                )

                Divider(
                    color = LightGrey,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(5.dp)
                        .padding(top = 20.dp)
                )

                Text(text = "2단계- 알림 시간 설정",
                    fontSize=15.sp,
                    modifier= Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 20.dp)

                )

                Button (
                    modifier= Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 10.dp),
                    colors = ButtonDefaults.buttonColors(LightGrey),
                    onClick={
                        timePickerDialog.show()
                    }

                ){
                    Text(text = "시간 설정하러 가기",
                        color= Color.Black,
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

                Text(text = "3단계- 루틴 목표 세우기",
                    fontSize=15.sp,
                    modifier= Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 20.dp)

                )


                LazyColumn(content = {
                    items(goal_list.size){
                        index-> goal_list_Item(goal = goal_list[index])
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()

                ){
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Add, contentDescription = null)

                    }

                    Text(text = "직접 입력",
                        color= Color.Black,
                        modifier = Modifier.padding(top=10.dp)
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = { /*TODO*/ },
                    modifier=Modifier.fillMaxWidth()
                        .align(Alignment.End)//왜 안되냐,,
                    , shape = RoundedCornerShape(2.dp)

                    ,
                    colors = ButtonDefaults.buttonColors(LightBlue),

                    ) {
                    Text(text = "습관 설정 완료",
                        color= Color.White,
                    )
                    
                }


            }
        }


    )

}

@Composable
fun weekdaysItem(day:String){
    Text(
        text = "$day",
        fontSize = 15.sp,
        color= LightBlue,
        modifier = Modifier
            .border(1.dp, LightBlue, RectangleShape)
            .background(Color.White)
            .padding(12.dp)
    )
    Spacer(modifier = Modifier.padding(end=10.dp))
}

@Composable
fun goal_list_Item(goal:String){
    var checked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ,

        Arrangement.Start

    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
            checked=it
        }
        )

        Text(text = "$goal",
            color= Color.Black,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxHeight()

            )




    }
}

@Preview(showBackground = true)
@Composable
fun habbit_making_Preview() {
    NewTineProjectTheme {
        habbit_making_Screen()

    }
}