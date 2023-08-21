package com.example.newtineproject.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LoginNavGraph(navController: NavHostController) {
    NavHost(
        navController=navController,
        route=Graph.LOGIN,
        startDestination = LoginScreen.Signup.route
    ){
        SignupNavGraph(navController)


    }
}

sealed class LoginScreen (val route:String){
    data object Login:LoginScreen(route="login")
    data object Signup:LoginScreen(route="signup")

}

