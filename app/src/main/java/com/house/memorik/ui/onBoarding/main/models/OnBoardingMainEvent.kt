package com.house.memorik.ui.onBoarding.main.models

sealed class OnBoardingMainEvent {
    object OnBoardingMainScreenEntered : OnBoardingMainEvent()
    object OnBoardingFirstScreenPassed : OnBoardingMainEvent()
    object OnBoardingThirdScreenPassed : OnBoardingMainEvent()
    object OnBoardingMainLogInButtonClicked : OnBoardingMainEvent()
    object OnBoardingMainContinueButtonClicked : OnBoardingMainEvent()
}