package com.house.memorik.ui.onBoarding.main.childs.first.uiData

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.house.memorik.R

data class OnBoardingFirstFirstImage(
    val imageId: Int = R.drawable.image_friends,
    val offsetX: Dp = 0.dp,
    val offsetY: Dp = 0.dp,
    val imageSize: Dp = 50.dp,
    val enterDelay: Int = 0
)

val onBoardingFirstCircleImageList = listOf(
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading1,
        offsetX = (-90).dp,
        offsetY = 130.dp,
        imageSize = 140.dp,
        enterDelay = 100
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading2,
        offsetX = (20).dp,
        offsetY = 240.dp,
        imageSize = 140.dp,
        enterDelay = 50
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading3,
        offsetX = (60).dp,
        offsetY = 60.dp,
        imageSize = 180.dp,
        enterDelay = 150
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading4,
        offsetX = (180).dp,
        offsetY = 230.dp,
        imageSize = 120.dp,
        enterDelay = 120
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading5,
        offsetX = (230).dp,
        offsetY = 20.dp,
        imageSize = 110.dp,
        enterDelay = 270
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading6,
        offsetX = (270).dp,
        offsetY = 130.dp,
        imageSize = 120.dp,
        enterDelay = 170
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading7,
        offsetX = (320).dp,
        offsetY = 260.dp,
        imageSize = 60.dp,
        enterDelay = 130
    ),
    OnBoardingFirstFirstImage(
        imageId = R.drawable.image_on_borading8,
        offsetX = (390).dp,
        offsetY = 30.dp,
        imageSize = 160.dp,
        enterDelay = 210
    ),
    OnBoardingFirstFirstImage(
        offsetX = (410).dp,
        offsetY = 230.dp,
        imageSize = 120.dp,
        enterDelay = 90
    ),
)