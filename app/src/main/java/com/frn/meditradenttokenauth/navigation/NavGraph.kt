package com.frn.meditradenttokenauth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.frn.meditradenttokenauth.presentation.login.LoginScreen
import com.frn.meditradenttokenauth.presentation.main.MainScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navHostController = navController)
        }
    }

}
