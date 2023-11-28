package com.yash.tweetsyapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yash.tweetsyapp.api.TweetsyAPI
import com.yash.tweetsyapp.screens.CategoryScreen
import com.yash.tweetsyapp.screens.DetailScreen
import com.yash.tweetsyapp.ui.theme.TweetsyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            TweetsyAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                    Text(text = "Tweetsy")
                            } ,
                            backgroundColor = Color.Black ,
                            contentColor = Color.White
                        )
                    }
                ){
                    Box(modifier = Modifier.padding(it)){
                        App()
                    }
                }
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "category"){
            composable(route = "category"){
                CategoryScreen {
                    navController.navigate("detail/${it}")
                }
            }
            composable(route = "detail/{category}" ,
                    arguments = listOf(
                        navArgument("category"){
                            type = NavType.StringType
                        }
                    )
                ){
                DetailScreen()
            }
        }
    }
}

// https://api.jsonbin.io/v3/b/64b3dd858e4aa6225ebf1315?meta=false
