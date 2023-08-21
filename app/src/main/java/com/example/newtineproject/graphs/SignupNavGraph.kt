package com.example.newtineproject.graphs

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.HomeScreen
import com.example.newtineproject.ui.screens.home.notification.NotificationScreen
import com.example.newtineproject.ui.screens.login.screens.LoginScreen
import com.example.newtineproject.ui.screens.login.screens.SignupAgreementScreen
import com.example.newtineproject.ui.screens.login.screens.SignupFinishScreen
import com.example.newtineproject.ui.screens.login.screens.SignupIdPwScreen
import com.example.newtineproject.ui.screens.login.screens.SignupPhoneVerificationScreen
import com.example.newtineproject.ui.screens.login.screens.SignupPressScreen
import com.example.newtineproject.ui.screens.login.screens.SignupProfileScreen
import com.example.newtineproject.ui.screens.login.screens.SignupSelectTopicsScreen
import com.example.newtineproject.ui.screens.main.MainScreen

fun NavGraphBuilder.SignupNavGraph(navController: NavHostController) {
    navigation(
        route = "Signup",
        startDestination =SignupScreen.Login.route
    ){
        //여기에 로그인 루트 추가하고 로그인 화면에서 id,pw같으면 route=Graph.Main 으로 걍ㄱㄱ
        composable(route=SignupScreen.Login.route){
            LoginScreen(navController =navController)
        }

        composable(route=SignupScreen.IdPw.route){
            SignupIdPwScreen(navController=navController)
        }
        composable(route=SignupScreen.Profile.route){
            SignupProfileScreen(navController=navController)
        }
        composable(route=SignupScreen.PhoneVerification.route){
            SignupPhoneVerificationScreen(navController=navController)
        }
        composable(route=SignupScreen.Press.route){
            SignupPressScreen(navController=navController)
        }
        composable(route=SignupScreen.Topics.route){
            SignupSelectTopicsScreen(navController=navController)
        }
        composable(route=SignupScreen.Agreement.route){
            SignupAgreementScreen(navController=navController)
        }
        composable(route=SignupScreen.Finish.route){
            SignupFinishScreen(navController=navController)
        }
        composable(route= SignupScreen.GotoMain.route){
            MainScreen()
        }
    }
}

sealed class SignupScreen(val route:String){

    data object Login:SignupScreen(route="Login")
    data object IdPw:SignupScreen(route = "IdPw")
    data object Profile:SignupScreen(route="Profile")
    data object PhoneVerification:SignupScreen(route="PhoneVerification")
    data object Press:SignupScreen(route="Press")
    data object Topics:SignupScreen(route="Topics")
    data object Agreement:SignupScreen(route="Agreement")
    data object Finish:SignupScreen(route="Finish")
    data object GotoMain:SignupScreen(route="GotoMain")


}