package com.house.memorik.ui.onBoarding.main.childs.third

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.house.memorik.R
import com.house.memorik.ui.onBoarding.main.childs.third.uiData.circlesList
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState
import com.house.memorik.ui.theme.LightGray
import com.house.memorik.ui.theme.MemorikTheme
import com.house.memorik.ui.theme.Orange

@Composable
fun OnBoardingThirdView(state: OnBoardingMainState.Display) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnBoardingThirdCirclesSection(isVisible = state.wasThirdScreenAnimated)
        TitleText()
        Spacer(modifier = Modifier.height(20.dp))
        SubtitleText()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingThirdCirclesSection(isVisible: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentAlignment = Alignment.Center
    ) {
        OnBoardingThirdCircles(isVisible)
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(
                    delayMillis = 200
                )
            ) + scaleIn(
                animationSpec = tween(
                    delayMillis = 200
                )
            )
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Orange.copy(alpha = 0.5f),
                                Orange,
                                Orange
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(0.5f),
                    tint = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingThirdCircles(isVisible: Boolean = false) {
    for (circle in circlesList) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(
                    delayMillis = circle.delayAnimTime
                )
            ) + scaleIn(
                animationSpec = tween(
                    delayMillis = circle.delayAnimTime
                )
            )
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                val path = Path().apply {
                    drawCircle(
                        color = circle.color,
                        center = Offset(x = width * 0.5f, y = height * 0.5f),
                        radius = size.minDimension / circle.radiusPercentage
                    )
                }
                drawPath(path, color = Color.Transparent)
            }
        }

    }
}

@Composable
fun TitleText() {
    Text(
        text = stringResource(id = R.string.on_boarding_third_screen_explore),
        style = MaterialTheme.typography.h1
    )
}

@Composable
fun SubtitleText() {
    Text(
        modifier = Modifier.padding(horizontal = 50.dp),
        text = stringResource(id = R.string.on_boarding_third_screen_explore_subtitle),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun OnBoardingThirdPreview() {
    MemorikTheme {
        OnBoardingThirdView(
            state = OnBoardingMainState.Display()
        )
    }
}