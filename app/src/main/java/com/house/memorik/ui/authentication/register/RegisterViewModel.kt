package com.house.memorik.ui.authentication.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.house.memorik.base.EventHandler
import com.house.memorik.ui.authentication.register.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel(), EventHandler<RegisterEvent> {

    private val _authenticationViewState: MutableLiveData<RegisterViewState> = MutableLiveData(initialState())
    val authenticationViewState: LiveData<RegisterViewState> = _authenticationViewState

    override fun obtainEvent(event: RegisterEvent) {
        when (val currentState = authenticationViewState.value) {
            is RegisterViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: RegisterEvent, currentState: RegisterViewState.Display) {
        when (event) {
            is RegisterEvent.OpenSection -> changeSection(event.section, currentState)
            is RegisterEvent.FillLoginUsername -> logInUsernameUpdate(event.username, currentState)
            is RegisterEvent.FillLoginPassword -> logInPasswordUpdate(event.password, currentState)
            is RegisterEvent.FillRegisterEmail -> registerEmailUpdate(event.email, currentState)
            is RegisterEvent.FillRegisterUsername -> registerUsernameUpdate(event.username, currentState)
            is RegisterEvent.FillRegisterPassword -> registerPasswordUpdate(event.password, currentState)
            else -> {}
        }
    }

    private fun changeSection(section: AuthenticationSection, currentState: RegisterViewState.Display) {
        val displayUpdate = currentState.copy(
            authenticationSection = section
        )
        _authenticationViewState.postValue(displayUpdate)
    }

    private fun logInUsernameUpdate(username: String, currentState: RegisterViewState.Display) {
        val displayUpdate = currentState.copy(
            loginData = currentState.loginData.copy(
                username = username
            )
        )
        _authenticationViewState.postValue(displayUpdate)
    }

    private fun logInPasswordUpdate(password: String, currentState: RegisterViewState.Display) {
        val displayUpdate = currentState.copy(
            loginData = currentState.loginData.copy(
                password = password
            )
        )
        _authenticationViewState.postValue(displayUpdate)
    }

    private fun registerEmailUpdate(email: String, currentState: RegisterViewState.Display) {
        val displayUpdate = currentState.copy(
            registerData = currentState.registerData.copy(
                email = email
            )
        )
        _authenticationViewState.postValue(displayUpdate)
    }

    private fun registerUsernameUpdate(username: String, currentState: RegisterViewState.Display) {
        val displayUpdate = currentState.copy(
            registerData = currentState.registerData.copy(
                username = username
            )
        )
        _authenticationViewState.postValue(displayUpdate)
    }

    private fun registerPasswordUpdate(password: String, currentState: RegisterViewState.Display) {
        val displayUpdate = currentState.copy(
            registerData = currentState.registerData.copy(
                password = password
            )
        )
        _authenticationViewState.postValue(displayUpdate)
    }

    private fun initialState() = RegisterViewState.Display(
        authenticationSection = AuthenticationSection.LOG_IN,
        loginData = LoginFields(),
        registerData = RegisterFields()
    )
}