@file:OptIn(ExperimentalAnimationApi::class)

package com.house.memorik.ui.authentication.register

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.house.memorik.ui.authentication.register.models.RegisterViewState
import com.house.memorik.ui.authentication.register.views.RegisterView

@Composable
fun RegisterScreen(
    authenticationViewModel: RegisterViewModel,
    onForgotPasswordClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    val viewState = authenticationViewModel.authenticationViewState.observeAsState()
    when (val state = viewState.value) {
        is RegisterViewState.Display -> {
            RegisterView(
                authenticationViewModel = authenticationViewModel,
                uiState = state,
                onForgotPasswordClicked = {
                    onForgotPasswordClicked()
                },
                onLoginButtonClicked = {
                    onLoginButtonClicked()
                }
            )
        }
        else -> {}
    }
}
