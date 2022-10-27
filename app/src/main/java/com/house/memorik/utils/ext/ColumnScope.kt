package com.house.memorik.utils.ext

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ColumnScope.AnimatedFadingVisibilityWithDelay(
    visible: Boolean,
    enterDelay: Int = 0,
    exitDelay: Int = 0,
    enterDuration: Int = 300,
    exitDuration: Int = 300,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = enterDuration,
                delayMillis = enterDelay
            )
        ) + scaleIn(
            animationSpec = tween(
                delayMillis = enterDelay
            ),
            initialScale = 0.9f,
            transformOrigin = TransformOrigin(0.5f, 1f)
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = exitDuration,
                delayMillis = exitDelay
            )
        ) + scaleOut(
            animationSpec = tween(
                delayMillis = exitDelay
            ),
            targetScale = 0.9f,
            transformOrigin = TransformOrigin(0.5f, 1f)
        )
    ) {
        content()
    }
}