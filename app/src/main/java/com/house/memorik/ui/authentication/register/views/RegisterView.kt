package com.house.memorik.ui.authentication.register.views

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.house.memorik.R
import com.house.memorik.ui.authentication.register.RegisterViewModel
import com.house.memorik.ui.authentication.register.models.*
import com.house.memorik.ui.theme.LightGray
import com.house.memorik.ui.theme.MemorikTheme
import com.house.memorik.ui.theme.White

@Composable
fun RegisterView(
    authenticationViewModel: RegisterViewModel,
    uiState: RegisterViewState.Display,
    onForgotPasswordClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    val offsetY by animateDpAsState(
        targetValue = if (uiState.authenticationSection == AuthenticationSection.LOG_IN) startOffset else endOffset,
        animationSpec = tween(durationMillis = imageTransitionAnimationTime, easing = LinearOutSlowInEasing)
    )
    val scaleLogInText by animateFloatAsState(
        targetValue = if (uiState.authenticationSection == AuthenticationSection.LOG_IN) defaultScale else minScale,
        animationSpec = tween(durationMillis = imageTransitionAnimationTime, easing = LinearOutSlowInEasing)
    )
    val scaleRegisterText by animateFloatAsState(
        targetValue = if (uiState.authenticationSection == AuthenticationSection.REGISTER) defaultScale else minScale,
        animationSpec = tween(durationMillis = imageTransitionAnimationTime, easing = LinearOutSlowInEasing)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = verticalGradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterViewRegister(
            offsetY = offsetY,
            scaleRegisterText = scaleRegisterText,
            section = uiState.authenticationSection,
            email = uiState.registerData.email,
            username = uiState.registerData.username,
            password = uiState.registerData.password,
            onEmailChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.FillRegisterEmail(it))
            },
            onUsernameChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.FillRegisterUsername(it))
            },
            onPasswordChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.FillRegisterPassword(it))
            },
            onStateChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.OpenSection(AuthenticationSection.REGISTER))
            }
        )
        RegisterViewLogin(
            offsetY = offsetY,
            scaleLogInText = scaleLogInText,
            registerState = uiState.authenticationSection,
            username = uiState.loginData.username,
            password = uiState.loginData.password,
            onUsernameTextChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.FillLoginUsername(it))
            },
            onPasswordTextChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.FillLoginPassword(it))
            },
            onStateChanged = {
                authenticationViewModel.obtainEvent(RegisterEvent.OpenSection(AuthenticationSection.LOG_IN))
            },
            onForgotPasswordClicked = {
                onForgotPasswordClicked()
            },
            onLoginButtonCLicked = {
                onLoginButtonClicked()
            }
        )
    }
}

@Composable
@Preview
fun AuthenticationPreview() {
    MemorikTheme() {
        RegisterView(
            authenticationViewModel = RegisterViewModel(),
            uiState = RegisterViewState.Display(
                authenticationSection = AuthenticationSection.LOG_IN,
                loginData = LoginFields(
                    username = stringResource(id = R.string.authentication_username),
                    password = stringResource(id = R.string.authentication_password)
                ),
                registerData = RegisterFields(
                    email = stringResource(id = R.string.authentication_email),
                )
            ),
            onForgotPasswordClicked = {},
            onLoginButtonClicked = {}
        )
    }
}


private const val imageTransitionAnimationTime = 900
private const val defaultScale = 1f
private const val minScale = 0.7f
private val startOffset = (-220).dp
private val endOffset = 0.dp
private val titlePadding = 40.dp
private val verticalGradientColors = listOf(White, White, LightGray)
