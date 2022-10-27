package com.house.memorik

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.house.memorik.ui.theme.MemorikTheme

@Composable
fun Cloud() {
    val backgroundColor = listOf(Color(0xFF2078EE), Color(0xFF74E6FE))
    val sunColor = listOf(Color(0xFFFFC200), Color(0xFFFFE100))
    var isAnimated by remember {
        mutableStateOf(false)
    }
    var offsetXSun by remember {
        mutableStateOf(0f)
    }
    var offsetYSun by remember {
        mutableStateOf(0f)
    }
    var offsetXCloud by remember {
        mutableStateOf(0f)
    }
    var offsetYCloud by remember {
        mutableStateOf(0f)
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(backgroundColor)
            )
    ) {

        Canvas(
            modifier = Modifier
                .offset(
                    x = (offsetXCloud / LocalDensity.current.density).dp,
                    y = (offsetYCloud / LocalDensity.current.density).dp
                )
                .size(300.dp)
                .padding(16.dp)
                .clickable {
                    isAnimated = !isAnimated
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetXCloud += dragAmount.x
                        offsetYCloud += dragAmount.y
                    }
                }
        ) {
            val width = size.width
            val height = size.height
            val path = Path().apply {
                moveTo(width.times(.76f), height.times(.72f))
                cubicTo(
                    width.times(.93f),
                    height.times(.72f),
                    width.times(.98f),
                    height.times(.41f),
                    width.times(.76f),
                    height.times(.40f)
                )
                cubicTo(
                    width.times(.75f),
                    height.times(.21f),
                    width.times(.35f),
                    height.times(.21f),
                    width.times(.38f),
                    height.times(.50f)
                )
                cubicTo(
                    width.times(.25f),
                    height.times(.50f),
                    width.times(.20f),
                    height.times(.69f),
                    width.times(.41f),
                    height.times(.72f)
                )
                close()
            }
            drawPath(path = path, color = Color.White.copy(alpha = .90f))
        }
        Canvas(modifier = Modifier
            .offset(
                x = (offsetXSun / LocalDensity.current.density).dp,
                y = (offsetYSun / LocalDensity.current.density).dp
            )
            .size(150.dp)
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetXSun += dragAmount.x
                    offsetYSun += dragAmount.y
                }
            }
        ) {
            val width = size.width
            val height = size.height
            drawCircle(
                brush = Brush.verticalGradient(sunColor),
                radius = width.times(0.5f),
                center = Offset(width.times(.5f), height.times(.5f)),
            )
        }
    }
}

@Preview
@Composable
private fun CloudPreview() {
    MemorikTheme {
        Cloud()
    }
}