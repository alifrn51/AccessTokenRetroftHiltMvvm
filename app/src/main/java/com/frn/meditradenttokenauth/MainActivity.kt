package com.frn.meditradenttokenauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.frn.meditradenttokenauth.navigation.SetupNavGraph
import com.frn.meditradenttokenauth.ui.theme.MeditradentTokenAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditradentTokenAuthTheme {
                // A surface container using the 'background' color from the theme


                navController = rememberNavController()
                SetupNavGraph(navController = navController)



            }
        }
    }
}
