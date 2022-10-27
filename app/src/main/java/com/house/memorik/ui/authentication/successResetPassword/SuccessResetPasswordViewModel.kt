package com.house.memorik.ui.authentication.successResetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.house.memorik.base.EventHandler
import com.house.memorik.ui.authentication.successResetPassword.models.SuccessResetPasswordEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuccessResetPasswordViewModel @Inject constructor() : ViewModel(), EventHandler<SuccessResetPasswordEvent> {

    private val _navigateBackToForgotPasswordScreen: MutableSharedFlow<Unit> = MutableSharedFlow()
    val navigateBackToForgotPasswordScreen: SharedFlow<Unit> = _navigateBackToForgotPasswordScreen

    private val _navigateBackToAuthenticationScreen: MutableSharedFlow<Unit> = MutableSharedFlow()
    val navigateBackToAuthenticationScreen: SharedFlow<Unit> = _navigateBackToAuthenticationScreen

    override fun obtainEvent(event: SuccessResetPasswordEvent) {
        when (event) {
            is SuccessResetPasswordEvent.OnTryAnotherEmailAddressClicked -> navigateBackToForgotPasswordScreen()
            is SuccessResetPasswordEvent.OnSkipClicked -> navigateBackToAuthenticationScreen()
        }
    }

    private fun navigateBackToForgotPasswordScreen() {
        viewModelScope.launch {
            _navigateBackToForgotPasswordScreen.emit(Unit)
        }
    }

    private fun navigateBackToAuthenticationScreen() {
        viewModelScope.launch {
            _navigateBackToAuthenticationScreen.emit(Unit)
        }
    }
}