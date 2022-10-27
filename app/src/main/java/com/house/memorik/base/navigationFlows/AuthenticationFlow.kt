package com.house.memorik.base.navigationFlows

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.house.memorik.base.Authentication
import com.house.memorik.base.ForgotPassword
import com.house.memorik.base.MainScreen
import com.house.memorik.base.ResetPasswordSuccess
import com.house.memorik.ui.authentication.register.RegisterScreen
import com.house.memorik.ui.authentication.register.RegisterViewModel
import com.house.memorik.ui.authentication.forgotPassword.ForgotPasswordScreen
import com.house.memorik.ui.authentication.forgotPassword.ForgotPasswordViewModel
import com.house.memorik.ui.authentication.successResetPassword.SuccessResetPasswordScreen
import com.house.memorik.ui.authentication.successResetPassword.SuccessResetPasswordViewModel
import com.house.memorik.ui.main.MainScreen
import com.house.memorik.utils.ext.navigateWithClearingBackStack

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authenticationFlow(navController: NavController) {
    composable(
        route = Authentication.route
    ) {
        val authenticationViewModel = hiltViewModel<RegisterViewModel>()
        RegisterScreen(
            authenticationViewModel = authenticationViewModel,
            onForgotPasswordClicked = {
                navController.navigate(ForgotPassword.route)
            },
            onLoginButtonClicked = {
                navController.navigateWithClearingBackStack(MainScreen.route)
            }
        )
    }
    composable(
        route = ForgotPassword.route
    ) {
        val forgotPasswordViewModel = hiltViewModel<ForgotPasswordViewModel>()
        ForgotPasswordScreen(
            forgotPasswordViewModel = forgotPasswordViewModel,
            navigateToSuccessResetPasswordScreen = {
                navController.navigate(ResetPasswordSuccess.route)
            }
        )
    }
    composable(
        route = ResetPasswordSuccess.route
    ) {
        val successResetPasswordViewModel = hiltViewModel<SuccessResetPasswordViewModel>()
        SuccessResetPasswordScreen(
            viewModel = successResetPasswordViewModel,
            goBackToAuthenticationScreen = {
                navController.popBackStack(Authentication.route, false)
            },
            goBackToResetPasswordScreen = {
                navController.popBackStack()
            }
        )
    }
}