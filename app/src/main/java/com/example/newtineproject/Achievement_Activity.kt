package com.example.newtineproject

import android.app.DatePickerDialog
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NewTineProjectTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

class Achievement_Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewTineProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AchievementScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen() {
    val currentDate = LocalDate.now()
    val date=LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateString = currentDate.format(formatter)

    //date picker
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker= DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
            Toast.makeText(context, "선택한 날짜: $selectedDate", Toast.LENGTH_SHORT).show()
        },
        year,
        month,
        day
    )

    //mission goal list
    val goalsList = arrayOf(
        "하루에 기사 갯수 정하기",
        "하루 뉴틴에 머무는 시간 정하기",
        "하루 기사에 의견 남기기 갯수 정하기",

        )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("뉴테크") },
                navigationIcon = {
                    IconButton(
                        onClick = { /* Handle back navigation */ }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle more button click */ }
                    ) {
                        Icon(Icons.Filled.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        content = {padding->
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                ) {
                    // 습관 달성 현황
                    Text(
                        text = "습관 달성 현황",
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )

                    IconButton(
                        onClick = {
                            datePicker.show()
                        }
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "calendar"

                        )
                    }

                }

                Spacer(modifier = Modifier.padding(top=10.dp))
                // Calendar Grid
                val weekMap = getThisWeekMap()
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 5.dp)

                ){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        content = {
                            items(weekMap.size) { index ->
                                val dateEntry = weekMap.entries.elementAt(index)
                                GridItem(dateEntry.key, dateEntry.value)
                            }
                        }
                    )
                }


                // 오늘의 날짜
                Text(
                    text = "<$dateString>",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                )

                // Today's Checkmarks
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) {
                        Image(
                            painter = painterResource(id = R.drawable.blue_check),
                            contentDescription = "Checkmark Icon",
                            modifier = Modifier.size(72.dp)
                        )
                    }
                }

                // Mission
                Text(
                    text = "MISSION",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                // Mission Grid

//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(1),
//                    content = {
//                        items(goalsList.size) { index ->
//                            MissionItem(goalsList[index])
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 10.dp)
//                )

                LazyColumn(content = {
                    items(goalsList.size){index->
                        MissionItem(goal=goalsList[index])
                    }

                },
                    modifier = Modifier.fillMaxWidth()
                )

                // Reward Button
                Button(
                    onClick = { /* Handle reward button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp,bottom=10.dp)
                        .background(LightBlue)
                    ,
                    colors = ButtonDefaults.buttonColors(LightBlue)

                ) {
                    Text(
                        text = "오늘의 리워드 받기",
                        color = Color.White
                    )
                }
            }
        }
    )
}

@Composable
fun GridItem(dateString: String, dayOfWeek: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = dateString, color = Color.Gray)
        Text(text = dayOfWeek, color = Color.DarkGray)
    }
}

@Composable
fun getThisWeekMap():MutableMap<String,String>{
    val currentDate= LocalDate.now()
    val formatter= DateTimeFormatter.ofPattern("dd")

    val monday=currentDate.with(DayOfWeek.MONDAY)
    val weekMap= mutableMapOf<String,String>()

    for (i in 0 until 7){
        val date=monday.plusDays(i.toLong())
        val dateString=date.format(formatter)
        val dayOfWeek=date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREA)

        weekMap.put(dateString,dayOfWeek.substring(0,1))
    }
    return weekMap
}

@Composable
fun MissionItem(goal: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = goal, color = Color.Black, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.blue_check),
            contentDescription = "Mission Icon",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Top)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun AchievementPreview(){
    NewTineProjectTheme {
        AchievementScreen()
    }
}
