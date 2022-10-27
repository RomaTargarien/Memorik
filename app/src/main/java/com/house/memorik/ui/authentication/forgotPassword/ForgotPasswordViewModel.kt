package com.house.memorik.ui.authentication.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.house.memorik.base.EventHandler
import com.house.memorik.ui.authentication.forgotPassword.models.ForgotPasswordEvent
import com.house.memorik.ui.authentication.forgotPassword.models.ForgotPasswordViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel(), EventHandler<ForgotPasswordEvent> {

    private val _onSuccessPasswordReset: MutableSharedFlow<Unit> = MutableSharedFlow()
    val onSuccessPasswordReset: SharedFlow<Unit> = _onSuccessPasswordReset

    private val _onErrorPasswordReset: MutableSharedFlow<Unit> = MutableSharedFlow()
    val onErrorPasswordReset: SharedFlow<Unit> = _onErrorPasswordReset

    private val _forgotPasswordViewState: MutableLiveData<ForgotPasswordViewState> = MutableLiveData(initialState())
    val forgotPasswordViewState: LiveData<ForgotPasswordViewState> = _forgotPasswordViewState

    override fun obtainEvent(event: ForgotPasswordEvent) {
        when (val current = _forgotPasswordViewState.value) {
            is ForgotPasswordViewState.Display -> reduce(event, current)
            else -> {}
        }
    }

    private fun reduce(event: ForgotPasswordEvent, currentState: ForgotPasswordViewState.Display) {
        when (event) {
            is ForgotPasswordEvent.FillEmailTextField -> emailUpdate(event.email, currentState)
            is ForgotPasswordEvent.OnResetPasswordClicked -> resetPassword(currentState)
        }
    }

    private fun emailUpdate(email: String, currentState: ForgotPasswordViewState.Display) {
        val displayUpdate = currentState.copy(
            email = email,
            isResetButtonEnabled = isEmailValid(email)
        )
        _forgotPasswordViewState.postValue(displayUpdate)
    }

    private fun resetPassword(currentState: ForgotPasswordViewState.Display) {
        viewModelScope.launch {
            val displayUpdate = currentState.copy(
                isEmailTextFieldEnabled = false,
                isResetButtonEnabled = false,
                isResetButtonLoading = true
            )
            _forgotPasswordViewState.postValue(displayUpdate)
            val resultSuccess = isPasswordResetSuccess()
            if (resultSuccess) {
                val displayUpdateAfterResetting = currentState.copy(
                    isEmailTextFieldEnabled = true,
                    isResetButtonEnabled = true,
                    isResetButtonLoading = false
                )
                _forgotPasswordViewState.postValue(displayUpdateAfterResetting)
                _onSuccessPasswordReset.emit(Unit)
            } else {
                val displayUpdateAfterResetting = currentState.copy(
                    isEmailTextFieldEnabled = true,
                    isResetButtonEnabled = true,
                    isResetButtonLoading = false
                )
                _forgotPasswordViewState.postValue(displayUpdateAfterResetting)
                _onErrorPasswordReset.emit(Unit)
            }
        }
    }

    private suspend fun isPasswordResetSuccess() = withContext(Dispatchers.IO) {
        delay(3000)
        true
    }

    private fun isEmailValid(email: String): Boolean = email.length > VALID_EMAIL_LENGTH

    private fun initialState() = ForgotPasswordViewState.Display()

    companion object {
        private const val VALID_EMAIL_LENGTH = 5
    }
}