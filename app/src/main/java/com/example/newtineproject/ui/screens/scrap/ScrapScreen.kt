package com.example.newtineproject.ui.screens.scrap

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.article.Article
import com.example.newtineproject.ui.screens.home.article.components.ArticleItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrapScreen(navController: NavHostController = rememberNavController()) {

    var isDesignFolderClicked: Boolean by remember { mutableStateOf(true)}
    var isNewsLetterFolderClicked: Boolean by remember { mutableStateOf(false)}
    var isInterestListClicked: Boolean by remember { mutableStateOf(false)}


    fun resetOtherClickedFlags(clickedFlag: String){
        if (clickedFlag != "design") {
            isDesignFolderClicked = false
        }
        if (clickedFlag != "newsLetter") {
            isNewsLetterFolderClicked = false
        }
        if (clickedFlag != "interestList") {
            isInterestListClicked = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "스크랩") },
                actions = {
                    ClickableText(
                        text = AnnotatedString("편집"),
                        onClick = {
                            //편집하는 화면으로 넘어감
                        },
                        style = LocalTextStyle.current.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0x8A000000),
                            lineHeight = 16.sp
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            )
        },
        content = { padding->
            Column(
                modifier = Modifier
                    .padding(
                        top = padding.calculateTopPadding() + 4.dp
                    )
                    .fillMaxSize()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.scrap_add),
                        contentDescription = "add folder",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Box(
                        modifier = Modifier
                            .width(77.dp)
                            .height(65.dp)
                            .clickable {
                                isDesignFolderClicked = !isDesignFolderClicked
                                resetOtherClickedFlags("design")
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = if (isDesignFolderClicked) R.drawable.scrap_folder_clicked else R.drawable.scrap_folder_unclicked),
                            contentDescription = "scrap folder",
                        )
                        Text(
                            text = "디자인 참고",
                            style = LocalTextStyle.current.copy(
                                color = if (isDesignFolderClicked) Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    Box(
                        modifier = Modifier
                            .width(77.dp)
                            .height(65.dp)
                            .clickable {
                                isNewsLetterFolderClicked = !isNewsLetterFolderClicked
                                resetOtherClickedFlags("newsLetter")
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = if (isNewsLetterFolderClicked) R.drawable.scrap_folder_clicked else R.drawable.scrap_folder_unclicked),
                            contentDescription = "scrap folder",
                        )
                        Text(
                            text = "뉴스 레터",
                            style = LocalTextStyle.current.copy(
                                color = if (isNewsLetterFolderClicked) Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    Box(
                        modifier = Modifier
                            .width(77.dp)
                            .height(65.dp)
                            .clickable {
                                isInterestListClicked = !isInterestListClicked
                                resetOtherClickedFlags("interestList")
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = if (isInterestListClicked) R.drawable.scrap_folder_clicked else R.drawable.scrap_folder_unclicked),
                            contentDescription = "scrap folder",
                        )
                        Text(
                            text = "관심목록",
                            style = LocalTextStyle.current.copy(
                                color = if (isInterestListClicked) Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ){
                    if(isDesignFolderClicked){
                        DesignFolderList()}
                    if(isNewsLetterFolderClicked){
                        NewsLetterFolderList()
                    }
                    if(isInterestListClicked){
                        InterestListFolderList()
                    }
                }
            }
        }
    )
}

@Composable
fun DesignFolderList() {
    val articleLists = listOf(
        Article(
            id = 0,
            title = "필즈상 수상 1주년…'허준이 수학\n" +
                    "난제 연구소' 모레 연다",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 1,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "세계 첫 모바일 길잡이 티맵...22년 만에 가입자 2000만 명 태웠다",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 1,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "네이버 마켓에 이미지 솔루션 내놨더니 \"매출 3배 껑충\"",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 2,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "지질연, 호주 퀸스랜드와 핵심광물 공동연구 추진",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "한국일보",
            timePassed = 2,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "사우디 왕세자가 반길 희소식, \n" +
                    "MS-블리자드 인수 청신호",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "전자신문",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "아시아경제",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "한국경제",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "사우디 아라비아 왕세자가 반길 \n" +
                    "희소식, MS-블리자드 인수 청신호",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "디지털 데일리",
            timePassed = 4,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "환경부 \"테슬라 모델Y, 전기차 보조금 전액 받기 어려워\"",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "\"경영보다 컴공 전공자 필요\"…IT 신입채용 두배 늘린 한은\n",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "서울경제신문",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "‘프론트엔드냐, 백엔드냐’를 고민하는 당신에게\n",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "요즘IT",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "하이투자 “삼성전기 삼화콘덴서 포함 MLCC업체, IT용 부품 재고조정 일단락”",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "Business Post",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        )
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
    ) {
        items(articleLists) { article ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ArticleItem(article = article, onItemClick = {})
            }
            if (articleLists.indexOf(article) != articleLists.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 17.dp))
            }
        }
    }


}

@Composable
fun NewsLetterFolderList() {
    val articleLists = listOf(
        Article(
            id = 0,
            title = "필즈상 수상 1주년…'허준이 수학\n" +
                    "난제 연구소' 모레 연다",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 1,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "세계 첫 모바일 길잡이 티맵...22년 만에 가입자 2000만 명 태웠다",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 1,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "네이버 마켓에 이미지 솔루션 내놨더니 \"매출 3배 껑충\"",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 2,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "지질연, 호주 퀸스랜드와 핵심광물 공동연구 추진",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "한국일보",
            timePassed = 2,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "사우디 왕세자가 반길 희소식, \n" +
                    "MS-블리자드 인수 청신호",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "전자신문",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "아시아경제",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "한국경제",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "사우디 아라비아 왕세자가 반길 \n" +
                    "희소식, MS-블리자드 인수 청신호",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "디지털 데일리",
            timePassed = 4,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "환경부 \"테슬라 모델Y, 전기차 보조금 전액 받기 어려워\"",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "\"경영보다 컴공 전공자 필요\"…IT 신입채용 두배 늘린 한은\n",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "서울경제신문",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "‘프론트엔드냐, 백엔드냐’를 고민하는 당신에게\n",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "요즘IT",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "하이투자 “삼성전기 삼화콘덴서 포함 MLCC업체, IT용 부품 재고조정 일단락”",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "Business Post",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        )
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
    ) {
        items(articleLists) { article ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ArticleItem(article = article, onItemClick = {})
            }
            if (articleLists.indexOf(article) != articleLists.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 17.dp))
            }
        }
    }


}

@Composable
fun InterestListFolderList() {
    val articleLists = listOf(
        Article(
            id = 0,
            title = "필즈상 수상 1주년…'허준이 수학\n" +
                    "난제 연구소' 모레 연다",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 1,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "세계 첫 모바일 길잡이 티맵...22년 만에 가입자 2000만 명 태웠다",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 1,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "네이버 마켓에 이미지 솔루션 내놨더니 \"매출 3배 껑충\"",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 2,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "지질연, 호주 퀸스랜드와 핵심광물 공동연구 추진",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "한국일보",
            timePassed = 2,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "사우디 왕세자가 반길 희소식, \n" +
                    "MS-블리자드 인수 청신호",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "전자신문",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "아시아경제",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "한국경제",
            timePassed = 3,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "사우디 아라비아 왕세자가 반길 \n" +
                    "희소식, MS-블리자드 인수 청신호",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "디지털 데일리",
            timePassed = 4,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "환경부 \"테슬라 모델Y, 전기차 보조금 전액 받기 어려워\"",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "연합뉴스",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "\"경영보다 컴공 전공자 필요\"…IT 신입채용 두배 늘린 한은\n",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "서울경제신문",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "‘프론트엔드냐, 백엔드냐’를 고민하는 당신에게\n",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "요즘IT",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        ),
        Article(
            id = 0,
            title = "하이투자 “삼성전기 삼화콘덴서 포함 MLCC업체, IT용 부품 재고조정 일단락”",
            thumbnail = "https://www.nasa.gov/sites/default/files/thumbnails/image/main_image_star-forming_region_carina_nircam_final-5mb.jpg",
            publication = "Business Post",
            timePassed = 5,
            category = "IT",
            contents = "기사 내용~"
        )
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
    ) {
        items(articleLists) { article ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ArticleItem(article = article, onItemClick = {})
            }
            if (articleLists.indexOf(article) != articleLists.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 17.dp))
            }
        }
    }


}