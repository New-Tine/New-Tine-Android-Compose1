package com.example.newtineproject.graphs.navigation_bar_items

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.mypage.UserProfile
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.mypage.MyPageScreen
import com.example.newtineproject.ui.screens.mypage.TmpNextScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        NavHost(
            navController = navController,
            route = NavigationBarScreen.MyPage.route,
            startDestination = MyPageDetailScreen.MyPage.route
        ) {
            composable(route = MyPageDetailScreen.MyPage.route) {
                val context=LocalContext.current.applicationContext
               MyPageScreen(
                    navController = navController,
                    userProfile = UserProfile(
                        userName = getUserEmail(context).toString(),
                        userCointPoints = 1000,
                        daysOfUsing = 3,
                        interestKeyword = mutableListOf({"부동산"},{"안드로이드"},{"IT"},{"국제정치"},{"문화"},{"블록체인"},{"디자인"},{"여행"},{"등산"},),
                        userImage = R.drawable.userpic
                    )
                )
            }
            composable(route = MyPageDetailScreen.Second.route) {
                TmpNextScreen(navController = navController)
            }
        }
    }
}

fun getUserEmail(context: Context): String? {
    val preference = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
    return preference.getString("user_email", null)
}


sealed class MyPageDetailScreen(val route: String) {
    data object MyPage: MyPageDetailScreen(route = "MYPAGE")
    data object Second: MyPageDetailScreen(route = "SECOND")
}