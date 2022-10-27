package com.house.memorik.ui.authentication.forgotPassword.models

sealed class ForgotPasswordEvent {
    object OnResetPasswordClicked : ForgotPasswordEvent()
    class FillEmailTextField(val email: String): ForgotPasswordEvent()
}