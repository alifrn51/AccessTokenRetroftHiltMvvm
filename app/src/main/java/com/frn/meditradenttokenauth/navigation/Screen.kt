package com.frn.meditradenttokenauth.navigation


sealed class Screen(val route: String){

    object Login: Screen("login_screen")
    object Main: Screen("main")

}
