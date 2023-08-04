package com.example.newtineproject.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.newtineproject.R
import com.example.newtineproject.common.RippleDetail
import com.example.newtineproject.domain.model.home.CardContents
import com.example.newtineproject.domain.model.home.Category
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NewsContent
import com.example.newtineproject.ui.theme.NewsPressAndDate
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeHorizontalPager() {

    val sliderList = tmpList
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = sliderList.size / 2)

    HorizontalPager(
        pageCount = sliderList.size,
        state = pagerState,
        beyondBoundsPageCount = 3,
        pageSpacing = 20.dp,
        contentPadding = PaddingValues(horizontal = 41.dp),
    ) { page ->
        val card = sliderList[page % sliderList.size]
        val viewModel = viewModel<HomeViewModel>()
        Box {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .graphicsLayer {
                        // For better animation but need to put bookmark on the other part
//                        val pageOffset =
//                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        shape = RoundedCornerShape(10.dp)
                        shadowElevation = 10f
//                        lerp(
//                            start = 0.85f,
//                            stop = 1f,
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        )
//                            .also { scale ->
//                                scaleX = scale
//                                scaleY = scale
//                            }
//                        alpha = lerp(
//                            start = 0.5f,
//                            stop = 1f,
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        )
                    }
            ) {
                Column {
                    ImageWithButtons(
                        image = card.image,
                        buttonList = card.buttonList
                    )
                    NewsPart(
                        newsTitle = card.newsTitle,
                        press = card.press,
                        date = card.date,
                        newsContent = card.newsContent
                    )
                }
            }

            // Bookmark
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconToggleButton(
                    checked = viewModel.getToggleState(page),
                    onCheckedChange = { checked ->
                        viewModel.setToggleState(page, checked)
                    },
                    modifier = Modifier.height(40.dp),
                    interactionSource = RippleDetail.NoRippleInteractionSource()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark),
                        contentDescription = null,
                        tint = viewModel.getCurrentColor(page),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

    }
    Spacer(modifier = Modifier.height(29.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(sliderList.size) {
            val selectedColor =
                if (pagerState.currentPage == it) LightBlue else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .size(6.dp)
                    .background(selectedColor)
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    }
            )
        }
    }
}

@Composable
fun ImageWithButtons(
    image: Any?,
    buttonList: List<Category>
) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.image_place_holder),
            error = painterResource(id = R.drawable.image_error),
            modifier = Modifier.height(175.dp),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp, vertical = 7.dp)
        ) {
            buttonList.map {
                Spacer(modifier = Modifier.width(7.dp))
                ButtonOnImage(it.categoryName)
            }
        }
    }
}


@Composable
fun ButtonOnImage(
    categoryName: String
) {
    CompositionLocalProvider(LocalRippleTheme provides RippleDetail.RippleCustomTheme) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, Color.White),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0x4D000000),

                ),
            modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 7.dp)
        ) {
            Text(
                text = categoryName,
                style = LocalTextStyle.current.copy(color = Color.White)
            )
        }
    }
}

@Composable
fun NewsPart(
    newsTitle: String,
    press: String,
    date: String,
    newsContent: String,
) {
    Column(
        modifier = Modifier
            .height(220.dp)
            .background(Color.White)
            .padding(horizontal = 21.dp, vertical = 15.dp)
    ) {
        Text(
            text = newsTitle,
            style = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            ),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                text = press,
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(300),
                    color = NewsPressAndDate
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = date,
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(300),
                    color = NewsPressAndDate
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = newsContent,
            style = LocalTextStyle.current.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight(300),
                color = NewsContent
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}


val tmpList = listOf(
    CardContents(
        image = "https://picsum.photos/id/210/900/1000",
        buttonList = listOf(Category.IT_SCIENCE, Category.INDUSTRY),
        newsTitle = "사우디 아라비아 왕세자가 반길 희소식, MS-블리자드 인수 청신호",
        press = "디지털 데일리",
        date = "2023.07.23 11:20",
        newsContent = "마이크로소프트(MS)가 소니 플레이스테이션(PS)에서 액티비전 블리자드(이하 블리자드) 인기 게임인 ‘콜 오브 듀티’(Call of Duty)를 계속해서 즐길 수 있도록 했다.\n"
    ),
    CardContents(
        image = "https://picsum.photos/id/201/800/450",
        buttonList = listOf(Category.IT_SCIENCE, Category.INDUSTRY),
        newsTitle = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
        press = "한국경제",
        date = "2023.07.22 19:48",
        newsContent = "메타가 이달 초 출시해 닷새 만에 역대 최단 기간 1억 가입자를 확보하며 돌풍을 일으킨 소셜미디어(SNS) 스레드의 인기가 시들해진 것으로 나타났다. "
    ),
    CardContents(
        image = "https://picsum.photos/id/202/800/450",
        buttonList = listOf(Category.IT_SCIENCE, Category.INDUSTRY),
        newsTitle = "환경부 \"테슬라 모델 Y, 전기차 보조금 전액 받기 어려워\"",
        press = "연합뉴스",
        date = "2023.07.23 15:20",
        newsContent = "중국에서 생산된 상대적으로 저렴한 테슬라 스포츠 유틸리티(SUV) 전기차 모델Y도 전기차 구매 보조금을 전액 받을 수 없을 것이라는 취지의 설명을 환경부가 내놨다."
    ),
    CardContents(
        image = "https://picsum.photos/id/203/800/450",
        buttonList = listOf(Category.IT_SCIENCE, Category.INDUSTRY),
        newsTitle = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
        press = "아시아 경제",
        date = "2023.07.22 17:48",
        newsContent = "한때 편의점 매대를 가득 채우던 수제맥주가 일본맥주에 밀리고 하이볼에 밀리며 방을 빼야 할 위기에 몰리고 있다. 편의점은 수제맥주를 대중에게 알리는 역할을 효과적으로 수행하며..."
    ),
    CardContents(
        image = "https://picsum.photos/id/204/800/450",
        buttonList = listOf(Category.IT_SCIENCE, Category.INDUSTRY),
        newsTitle = "환경부 \"테슬라 모델 Y, 전기차 보조금 전액 받기 어려워\"",
        press = "연합뉴스",
        date = "2023.07.23 15:20",
        newsContent = "중국에서 생산된 상대적으로 저렴한 테슬라 스포츠 유틸리티(SUV) 전기차 모델Y도 전기차 구매 보조금을 전액 받을 수 없을 것이라는 취지의 설명을 환경부가 내놨다."
    )
)