package com.house.memorik.ui.onBoarding.main.models

sealed class OnBoardingMainState {
    data class Display(
        val wasMainScreenAnimated: Boolean = false,
        val wasFirstScreenAnimated: Boolean = false,
        val wasThirdScreenAnimated: Boolean = false
    ) : OnBoardingMainState()
}