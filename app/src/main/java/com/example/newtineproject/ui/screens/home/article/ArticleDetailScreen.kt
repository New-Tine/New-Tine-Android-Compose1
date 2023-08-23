package com.example.newtineproject.ui.screens.home.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newtineproject.R
import com.example.newtineproject.common.Url
import com.example.newtineproject.data.remote.article_detail_service.ArticleDetailService
import com.example.newtineproject.data.remote.dto.article_detail.ArticleDetail
import com.example.newtineproject.data.remote.dto.article_detail.ArticleDetailResult
import com.example.newtineproject.ui.theme.LightBlue

const val bearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicm9sZXMiOiJVU0VSIiwiaWF0IjoxNjkyMjU3NTM1LCJleHAiOjE2OTM0NjcxMzV9.ZH-ZlaovYVZvYo9Fa3fInq2x5NSm6b1ZSNZoPQ0zNgg"
val articleDetailService = ArticleDetailService.create(bearerToken)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen (
    navController: NavController,
    newsId: Long,
    paddingValues: PaddingValues
){

    var clicked by remember{ mutableStateOf(false) }

    
    val eachArticleDetail = produceState(
        initialValue = ArticleDetail(
            code = 0,
            isSuccess = false,
            message = "",
            result = ArticleDetailResult(
                category = "",
                content = "",
                createdAt= "",
                pressImage = "",
                pressName = "",
                pressSubscriber = 0,
                newsImg = "",
                title = "",
                scrapped = false,
                subscribed = false,
                newsID = 0
            )
        ),
        producer = {
            value = articleDetailService.getEachArticleDetail(newsId = newsId)
            println(value)
        }
    )

    println("Here!!$newsId")
    println(Url.EACH_ARTICLE+"/${newsId}")

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}){
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                        tint = Color.Gray)
                }
            },
            title={ Text(text = "")},
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(painter = painterResource(id = R.drawable.messageadd), contentDescription ="scrap" )
                }

                IconButton(onClick = {
                    clicked = !clicked
                }) {
                    Image(painter= painterResource(id = R.drawable.scrap), contentDescription = "bookmark",
                        colorFilter = if(clicked){
                            ColorFilter.tint(LightBlue)
                        }
                        else{
                            null
                        }

                        )

                }
            }

    ) },
        content = { padding ->
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
                    .padding(top = 50.dp)
            ) {
                Row (modifier = Modifier.fillMaxWidth()){
                    Box(modifier = Modifier
                        .wrapContentHeight()
                        .background(
                            color = Color.Transparent
                        )
                        .border(1.dp, LightBlue, RoundedCornerShape(20.dp))

                        ){
                        Text(text = eachArticleDetail.value.result.category,
                            color = LightBlue,
                            modifier= Modifier
                                .padding(5.dp)
                                .padding(start = 5.dp, end = 5.dp)
                            )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row (modifier = Modifier.fillMaxWidth()){
                    Text(text = eachArticleDetail.value.result.title,
                        fontSize = 20.sp
                        , fontWeight = FontWeight.Bold
                        )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = eachArticleDetail.value.result.pressName,
                        Modifier.padding(top=10.dp)
                        )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.height(35.dp)
                        , colors = ButtonDefaults.buttonColors(LightBlue)
                    ) {
                        Text(text = "구독",
                            color = Color.White,
                        )

                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "등록: ${eachArticleDetail.value.result.createdAt}",
                        Modifier.padding(top=10.dp)
                        )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    Arrangement.Center
                ){
                    AsyncImage(model = eachArticleDetail.value.result.newsImg, contentDescription = "thumbnail")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier=Modifier.fillMaxWidth()
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ){
                        Text(
                            text = eachArticleDetail.value.result.content,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    )
}