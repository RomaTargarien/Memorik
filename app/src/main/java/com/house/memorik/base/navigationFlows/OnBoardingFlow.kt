package com.house.memorik.base.navigationFlows

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.house.memorik.base.*
import com.house.memorik.ui.onBoarding.OnBoardingFirstScreen
import com.house.memorik.ui.onBoarding.OnBoardingMain
import com.house.memorik.ui.onBoarding.main.OnBoardingMainViewModel
import com.house.memorik.ui.onBoarding.finish.finish.OnBoardingFinish
import com.house.memorik.ui.onBoarding.main.childs.second.OnBoardingSecondScreen
import com.house.memorik.ui.onBoarding.main.childs.third.OnBoardingThirdScreen
import com.house.memorik.utils.ext.navigateWithClearingBackStack

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onBoardingFlow(navController: NavController) {
    composable(
        route = OnBoardingMain.route,
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Down, tween(durationMillis = defaultAnimTime), targetOffset = {
                1
            })
        }
    ) {
        val viewModel = hiltViewModel<OnBoardingMainViewModel>()
        OnBoardingMain(
            parentNavController = navController,
            viewModel = viewModel,
            navigateToAuthenticationScreen = {
                navController.navigateWithClearingBackStack(route = OnBoardingFinish.route)
            }
        )
    }
    composable(
        route = OnBoardingFinish.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Up, tween(durationMillis = defaultAnimTime))
        }
    ) {
        OnBoardingFinish {
            navController.navigateWithClearingBackStack(route = Authentication.route)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onBoardingFlowChild(viewModel: OnBoardingMainViewModel) {
    composable(
        route = OnBoardingFirst.route,
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(durationMillis = defaultAnimTime), targetOffset = {
                1
            })
        },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Right, tween(durationMillis = defaultAnimTime), initialOffset = {
                (it * 2)
            })
        }
    ) {
        OnBoardingFirstScreen(viewModel = viewModel)
    }
    composable(
        route = OnBoardingSecond.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(durationMillis = defaultAnimTime))
        },
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(durationMillis = defaultAnimTime), targetOffset = {
                1
            })
        },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Right, tween(durationMillis = defaultAnimTime), initialOffset = {
                it
            })
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(durationMillis = defaultAnimTime))
        }
    ) {
        OnBoardingSecondScreen(viewModel = viewModel)
    }
    composable(
        route = OnBoardingThird.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(durationMillis = defaultAnimTime))
        },
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(durationMillis = defaultAnimTime), targetOffset = {
                1
            })
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(durationMillis = defaultAnimTime))
        }
    ) {
        OnBoardingThirdScreen(viewModel = viewModel)
    }
}

private const val defaultAnimTime = 600

