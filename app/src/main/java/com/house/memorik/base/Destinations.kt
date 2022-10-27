package com.house.memorik.base

interface Destination {
    val route: String
}

object Authentication : Destination {
    override val route: String = "Authentication"
}

object ForgotPassword : Destination {
    override val route: String = "ForgotPassword"
}

object ResetPasswordSuccess : Destination {
    override val route: String = "ResetPasswordSuccess"
}

object OnBoardingMain : Destination {
    override val route: String = "OnBoardingMain"
}

object OnBoardingFirst : Destination {
    override val route: String = "OnBoardingFirst"
}

object OnBoardingSecond : Destination {
    override val route: String = "OnBoardingSecond"
}

object OnBoardingThird : Destination {
    override val route: String = "OnBoardingThird"
}

object OnBoardingFinish : Destination {
    override val route: String = "OnBoardingFinish"
}

object MainScreen : Destination {
    override val route: String = "MainScreen"
}