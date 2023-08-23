package com.example.newtineproject.ui.screens.home.article

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.newtineproject.data.remote.article_detail_service.ArticleDetailService
import com.example.newtineproject.data.remote.article_detail_service.ArticleDetailServiceImpl
import com.example.newtineproject.data.remote.dto.article_detail.ArticleDetail
import com.example.newtineproject.data.remote.dto.article_detail.ArticleDetailResult
import com.example.newtineproject.domain.model.article.Article
import com.example.newtineproject.ui.theme.LightBlue

val articleDetailService = ArticleDetailService.create()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen (
    navController: NavController,
    newsId: Int
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
                title = "",
                scrapped = false,
                subscribed = false,
                newsID = 0
            )
        ),
        producer = {
            value = articleDetailService.getEachArticleDetail()
        }
    )



    Scaffold(
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
        content = {padding ->
            Column(
                Modifier
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
                    AsyncImage(model = eachArticleDetail.value.result.pressImage, contentDescription = "thumbnail")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier=Modifier.fillMaxWidth()
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .verticalScroll(rememberScrollState())
                    ){
                        Text(text = eachArticleDetail.value.result.content
                            , fontSize = 15.sp
                        )

                    }


                }



            }

        }
    )
}



//
//@Preview
//@Composable
//fun articleDetailPreview(){
//    val articleLists = listOf(
//        Article(
//            title = "필즈상 수상 1주년…'허준이 수학\n" +
//                    "난제 연구소' 모레 연다",
//            thumbnail = R.drawable.article1,
//            publication = "연합뉴스",
//            timePassed = 1,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "세계 첫 모바일 길잡이 티맵...22년 만에 가입자 2000만 명 태웠다",
//            thumbnail = R.drawable.article2,
//            publication = "연합뉴스",
//            timePassed = 1,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "네이버 마켓에 이미지 솔루션 내놨더니 \"매출 3배 껑충\"",
//            thumbnail = R.drawable.article3,
//            publication = "연합뉴스",
//            timePassed = 2,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "지질연, 호주 퀸스랜드와 핵심광물 공동연구 추진",
//            thumbnail = R.drawable.article4,
//            publication = "한국일보",
//            timePassed = 2,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "사우디 왕세자가 반길 희소식, \n" +
//                    "MS-블리자드 인수 청신호",
//            thumbnail = R.drawable.article5,
//            publication = "전자신문",
//            timePassed = 3,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
//            thumbnail = R.drawable.article6,
//            publication = "아시아경제",
//            timePassed = 3,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
//            thumbnail = R.drawable.article7,
//            publication = "한국경제",
//            timePassed = 3,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "사우디 아라비아 왕세자가 반길 \n" +
//                    "희소식, MS-블리자드 인수 청신호",
//            thumbnail = R.drawable.article8,
//            publication = "디지털 데일리",
//            timePassed = 4,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "환경부 \"테슬라 모델Y, 전기차 보조금 전액 받기 어려워\"",
//            thumbnail = R.drawable.article9,
//            publication = "연합뉴스",
//            timePassed = 5,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "\"경영보다 컴공 전공자 필요\"…IT 신입채용 두배 늘린 한은\n",
//            thumbnail = R.drawable.article10,
//            publication = "서울경제신문",
//            timePassed = 5,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "‘프론트엔드냐, 백엔드냐’를 고민하는 당신에게\n",
//            thumbnail = R.drawable.article11,
//            publication = "요즘IT",
//            timePassed = 5,
//            category = "IT",
//            contents = "기사 내용~"
//        ),
//        Article(
//            title = "하이투자 “삼성전기 삼화콘덴서 포함 MLCC업체, IT용 부품 재고조정 일단락”",
//            thumbnail = R.drawable.article12,
//            publication = "Business Post",
//            timePassed = 5,
//            category = "IT",
//            contents = "기사 내용~"
//        )
//    )
//
//    val test=Article(
//        title = "‘노회찬 평전’이 재점화한 ‘좋은 정치’로의 열망\n",
//        thumbnail = R.drawable.article13,
//        publication = "경향신문",
//        timePassed = 2023,
//        contents = "(서울=연합뉴스) 이재영 기자 = 중국에서 생산된 상대적으로 저렴한 테슬라 스포츠유틸리티(SUV) 전기차 모델Y도 전기차 구매 보조금을 전액 받을 수 없을 것이라는 취지의 설명을 환경부가 내놨다.\n" +
//                "\n" +
//                "환경부는 17일 설명자료에서 \"모델Y는 보조금 지급 대상인지를 확인하는 '전기차 보급 대상 평가'를 진행 중\"이라며 \"보조금이 지급될지도 아직 불확실하다\"고 밝혔다.\n" +
//                "\n" +
//                "그러면서 \"테슬라는 친환경차 보급 목표가 부여된 기업이 아니고 모델Y는 현행 규정상 혁신기술이 적용되지 않았다\"라고 덧붙였다.\n" +
//                "\n" +
//                "국고 전기승용차 구매 보조금 중 친환경차 보급 목표가 부여된 10개 제조사 차량에 주는 보조금(최대 140만원)과 혁신기술이 적용된 차에 지원되는 보조금(20만원)은 받기 어렵다는 취지의 설명이다. 올해 전기차 보조금 지침상 혁신기술은 전기차에서 외부로 전기를 끌어다 쓸 수 있는 '비히클 투 로드'(V2L)다.\n" +
//                "\n" +
//                "테슬라는 최근 국내에서 모델Y를 판매하기 시작했는데 홈페이지를 보면 가격이 5천699만원으로 전기승용차 보조금을 전액 받을 수 있는 기준선 '5천700만원 미만'에 해당한다.\n" +
//                "\n" +
//                "이에 모델Y가 전액 보조금에 힘입어 국내시장에서 인기를 끌 것이라는 전망이 나왔다.\n" +
//                "\n" +
//                "올해 전기승용차 보조금은 가격이 5천700만원 미만이어야 100% 받을 수 있고 '5천700만원 이상 8천500만원 미만'은 50%만 받을 수 있다. 찻값이 8천500만원 이상이면 보조금이 지원되지 않는다.\n" +
//                "\n" +
//                "전기승용차 국고 보조금으로는 1회 충전 시 주행거리 등을 기준으로 한 성능보조금(중대형 최대 500만원), 자동차제작사 친환경차 보급 목표 달성 여부에 따른 보조금(최대 140만원), 제작사 충전시설 확충 실적에 따른 보조금(20만원), 혁신기술 적용 여부에 따른 보조금(20만원) 등이 있다.\n" +
//                "\n" +
//                "jylee24@yna.co.kr\n" +
//                "\n",
//        category = "정치"
//    )
//
//    ArticleDetailScreen(navController = rememberNavController(), 3, articleList = articleLists)
//}
//
