package com.house.memorik.ui.onBoarding.main.childs.first

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.house.memorik.R
import com.house.memorik.ui.onBoarding.main.childs.first.uiData.onBoardingFirstCircleImageList
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState
import com.house.memorik.ui.theme.MemorikTheme


@Composable
fun OnBoardingFirstView(state: OnBoardingMainState.Display) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnBoardingFirstCircleImagesBox(isVisible = state.wasFirstScreenAnimated)
        TitleText(isVisible = state.wasFirstScreenAnimated)
        Spacer(modifier = Modifier.height(20.dp))
        SubtitleText(isVisible = state.wasFirstScreenAnimated)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingFirstCircleImagesBox(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
    ) {
        for (item in onBoardingFirstCircleImageList) {
            AnimatedVisibility(
                visible = isVisible,
                modifier = Modifier.offset(x = item.offsetX, y = item.offsetY),
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 700,
                        delayMillis = item.enterDelay
                    )
                ) + scaleIn(
                    animationSpec = tween(
                        durationMillis = 700,
                        delayMillis = item.enterDelay
                    )
                )
            ) {
                Image(
                    painter = painterResource(id = item.imageId),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(item.imageSize),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun TitleText(isVisible: Boolean = false) {
    AnimatedVisibility(visible = isVisible, enter = slideInVertically() + fadeIn()) {
        Text(
            text = stringResource(id = R.string.on_boarding_first_screen_explore),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun SubtitleText(isVisible: Boolean = false) {
    AnimatedVisibility(visible = isVisible, enter = slideInVertically() + fadeIn()) {
        Text(
            modifier = Modifier.padding(horizontal = 50.dp),
            text = stringResource(id = R.string.on_boarding_first_screen_explore_subtitle),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(widthDp = 480)
fun OnBoardingFirstPreview() {
    MemorikTheme {
        OnBoardingFirstView(
            state = OnBoardingMainState.Display()
        )
    }
}