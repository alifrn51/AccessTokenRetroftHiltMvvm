package com.frn.meditradenttokenauth.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navHostController: NavHostController) {


    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
        Text(text = "MainScreen")
    }
}