package com.house.memorik.ui.onBoarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.house.memorik.base.*
import com.house.memorik.base.navigationFlows.onBoardingFlowChild
import com.house.memorik.ui.onBoarding.main.OnBoardingMainView
import com.house.memorik.ui.onBoarding.main.OnBoardingMainViewModel
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainEvent
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState
import com.house.memorik.ui.theme.LightGray
import com.house.memorik.utils.ext.navigateWithClearingBackStack
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable

fun OnBoardingMain(
    navigateToAuthenticationScreen: () -> Unit,
    parentNavController: NavController,
    viewModel: OnBoardingMainViewModel
) {
    val childNavController = rememberAnimatedNavController()
    val state = viewModel.state.observeAsState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray)
    ) {
        when (val currentState = state.value) {
            is OnBoardingMainState.Display -> {
                OnBoardingScreensSection(childNavController = childNavController, viewModel = viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                OnBoardingMainView(viewModel = viewModel, state = currentState)
            }
            else -> {}
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(OnBoardingMainEvent.OnBoardingMainScreenEntered)
        scope.launch {
            viewModel.onContinueButtonClicked.collect {
                handleNavigation(childNavController, childNavController.currentDestination?.route) {
                    parentNavController.navigateWithClearingBackStack(route = OnBoardingFinish.route)
                }
            }
        }
        scope.launch {
            viewModel.onLoginButtonClicked.collect {
                parentNavController.navigateWithClearingBackStack(route = Authentication.route)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreensSection(
    childNavController: NavHostController,
    viewModel: OnBoardingMainViewModel
) {
    AnimatedNavHost(
        navController = childNavController,
        startDestination = OnBoardingFirst.route,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
    ) {
        onBoardingFlowChild(viewModel)
    }
}

private fun handleNavigation(navController: NavController, currentRoute: String?, navigateToAuthenticationScreen: () -> Unit) {
    when (currentRoute) {
        OnBoardingFirst.route -> navController.navigate(OnBoardingSecond.route)
        OnBoardingSecond.route -> navController.navigate(OnBoardingThird.route)
        OnBoardingThird.route -> navigateToAuthenticationScreen()
    }
}