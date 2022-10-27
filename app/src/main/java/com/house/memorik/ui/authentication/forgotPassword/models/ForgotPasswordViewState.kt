package com.house.memorik.ui.authentication.forgotPassword.models

import com.house.memorik.ui.authentication.register.models.EMPTY_STRING

sealed class ForgotPasswordViewState {
    data class Display(
        val email: String = EMPTY_STRING,
        val isEmailTextFieldEnabled: Boolean = true,
        val isResetButtonEnabled: Boolean = false,
        val isResetButtonLoading: Boolean = false
    ) : ForgotPasswordViewState()
}