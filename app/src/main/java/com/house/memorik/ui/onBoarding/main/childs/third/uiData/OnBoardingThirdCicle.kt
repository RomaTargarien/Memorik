package com.house.memorik.ui.onBoarding.main.childs.third.uiData

import androidx.compose.ui.graphics.Color

data class OnBoardingThirdCircle(
    val color: Color = Color.Gray,
    val radiusPercentage: Float = 2f,
    val delayAnimTime: Int = 0
)

val circlesList = listOf(
    OnBoardingThirdCircle(
        color = Color.Gray.copy(alpha = 0.05f),
        radiusPercentage = 1.8f,
        delayAnimTime = 50
    ),
    OnBoardingThirdCircle(
        color = Color.Gray.copy(alpha = 0.04f),
        radiusPercentage = 2.2f,
        delayAnimTime = 100
    ),
    OnBoardingThirdCircle(
        color = Color.Gray.copy(alpha = 0.03f),
        radiusPercentage = 2.9f,
        delayAnimTime = 150
    )
)