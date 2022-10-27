package com.house.memorik.ui.authentication.register.models

sealed class RegisterEvent {
    object EnterScreen : RegisterEvent()
    object RegistrationClicked : RegisterEvent()
    object LoginClicked : RegisterEvent()
    class OpenSection(val section: AuthenticationSection) : RegisterEvent()
    class FillLoginUsername(val username: String) : RegisterEvent()
    class FillLoginPassword(val password: String) : RegisterEvent()
    class FillRegisterEmail(val email: String) : RegisterEvent()
    class FillRegisterUsername(val username: String) : RegisterEvent()
    class FillRegisterPassword(val password: String) : RegisterEvent()
}

enum class AuthenticationSection {
    LOG_IN, REGISTER
}