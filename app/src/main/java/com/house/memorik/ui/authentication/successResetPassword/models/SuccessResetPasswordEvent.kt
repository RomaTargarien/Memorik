package com.house.memorik.ui.authentication.successResetPassword.models

sealed class SuccessResetPasswordEvent {
    object OnSkipClicked: SuccessResetPasswordEvent()
    object OnTryAnotherEmailAddressClicked : SuccessResetPasswordEvent()
}