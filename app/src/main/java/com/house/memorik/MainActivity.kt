package com.house.memorik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.house.memorik.base.MainScreen
import com.house.memorik.base.OnBoardingMain
import com.house.memorik.base.navigationFlows.authenticationFlow
import com.house.memorik.base.navigationFlows.onBoardingFlow
import com.house.memorik.ui.main.MainScreen
import com.house.memorik.ui.theme.MemorikTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemorikTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = Color.Black.copy(alpha = 0.8f)
                )
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = OnBoardingMain.route
                ) {
                    onBoardingFlow(navController)
                    authenticationFlow(navController)
                    composable(route = MainScreen.route) {
                        MainScreen()
                    }
                }
            }
        }
    }
}



