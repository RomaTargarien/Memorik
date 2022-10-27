package com.house.memorik.ui.authentication.forgotPassword

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.house.memorik.ui.authentication.forgotPassword.models.ForgotPasswordViewState
import com.house.memorik.ui.authentication.forgotPassword.views.ForgotPasswordView
import com.house.memorik.ui.theme.MemorikTheme
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    forgotPasswordViewModel: ForgotPasswordViewModel,
    navigateToSuccessResetPasswordScreen: () -> Unit
) {
    val state = forgotPasswordViewModel.forgotPasswordViewState.observeAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    when (val currentState = state.value) {
        is ForgotPasswordViewState.Display -> {
            Scaffold(
                modifier = Modifier,
                scaffoldState = scaffoldState,
            ) { p ->
                p.calculateBottomPadding()
                ForgotPasswordView(
                    forgotPasswordViewModel = forgotPasswordViewModel,
                    uiState = currentState
                )
            }
        }
        else -> {}
    }
    LaunchedEffect(key1 = true) {
        scope.launch {
            forgotPasswordViewModel.onSuccessPasswordReset.collect {
                navigateToSuccessResetPasswordScreen()
            }
        }
        scope.launch {
            forgotPasswordViewModel.onErrorPasswordReset.collect {
                scaffoldState.snackbarHostState.showSnackbar("Error")
            }
        }
    }
}

@Composable
@Preview
fun ForgotPasswordPreview() {
    MemorikTheme {
        ForgotPasswordScreen(
            forgotPasswordViewModel = ForgotPasswordViewModel(),
            navigateToSuccessResetPasswordScreen = {}
        )
    }
}