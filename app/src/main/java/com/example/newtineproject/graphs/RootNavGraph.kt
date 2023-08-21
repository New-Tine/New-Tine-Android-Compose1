package com.example.newtineproject.graphs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.ui.screens.login.KaKaoAuthViewModel
import com.example.newtineproject.ui.screens.login.MyApplication
import com.example.newtineproject.ui.screens.login.screens.LLoginScreen
import com.example.newtineproject.ui.screens.login.screens.LoginScreen
import com.example.newtineproject.ui.screens.login.screens.SignupFinishScreen
import com.example.newtineproject.ui.screens.login.screens.SignupIdPwScreen
import com.example.newtineproject.ui.screens.main.MainScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.LOGIN,
    ) {
        //로그인 여부에 따른 분기 처리 추가 필요
        composable(route=Graph.LOGIN){
            LLoginScreen()
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val LOGIN= "login_graph"
    const val SIGNUP="signup_graph"
    const val ARTICLE="Article_graph"
}