package com.house.memorik.ui.authentication.successResetPassword

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.house.memorik.ui.authentication.successResetPassword.views.SuccessResetPasswordView
import com.house.memorik.ui.theme.MemorikTheme

@Composable
fun SuccessResetPasswordScreen(
    viewModel: SuccessResetPasswordViewModel,
    goBackToAuthenticationScreen: () -> Unit,
    goBackToResetPasswordScreen: () -> Unit
) {
    SuccessResetPasswordView(
        viewModel = viewModel,
        goBackToAuthenticationScreen = { goBackToAuthenticationScreen() },
        goBackToResetPasswordScreen = { goBackToResetPasswordScreen() }
    )
}

@Preview
@Composable
fun SuccessResetPasswordScreenPreview() {
    MemorikTheme {
        SuccessResetPasswordScreen(
            viewModel = SuccessResetPasswordViewModel(),
            goBackToAuthenticationScreen = {},
            goBackToResetPasswordScreen = {}
        )
    }
}