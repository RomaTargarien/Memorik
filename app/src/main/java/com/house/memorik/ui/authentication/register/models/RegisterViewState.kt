package com.house.memorik.ui.authentication.register.models

sealed class RegisterViewState {
    data class Display(
        val authenticationSection: AuthenticationSection = AuthenticationSection.LOG_IN,
        val loginData: LoginFields,
        val registerData: RegisterFields
    ) : RegisterViewState()
}

data class LoginFields(
    val username: String = EMPTY_STRING,
    val password: String = EMPTY_STRING
)

data class RegisterFields(
    val email: String = EMPTY_STRING,
    val username: String = EMPTY_STRING,
    val password: String = EMPTY_STRING
)

const val EMPTY_STRING = ""