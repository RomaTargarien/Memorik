package com.house.memorik.ui.onBoarding.finish.finish

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house.memorik.ui.authentication.register.views.AuthenticationButton
import com.house.memorik.ui.theme.Gray
import com.house.memorik.ui.theme.LightGray
import com.house.memorik.ui.theme.MemorikTheme
import com.house.memorik.ui.theme.Orange
import kotlinx.coroutines.delay

@Composable
fun OnBoardingFinish(navigateToAuthenticationScreen: () -> Unit) {
    var isLogoAnimated by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogoCircle(
            isAnimating = isLogoAnimated
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "memorik.app",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(80.dp))
        AuthenticationButton(
            text = "Get Started",
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            navigateToAuthenticationScreen()
        }
        Spacer(modifier = Modifier.height(20.dp))
        LogInButton {
            navigateToAuthenticationScreen()
        }
        Spacer(modifier = Modifier.height(30.dp))
        PrivacyPolicyText()
    }
    LaunchedEffect(key1 = true) {
        delay(500)
        isLogoAnimated = true
    }
}

@Composable
fun PrivacyPolicyText(modifier: Modifier = Modifier) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                color = Gray,
                fontSize = 16.sp
            )
        ) {
            append("By selecting one of the other, you are agreeing")
            append("\n")
            append("to the")
        }
        append(" ")
        pushStringAnnotation(tag = "terms_of_service", annotation = "")
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                color = Orange,
                fontSize = 17.sp
            )
        ) {
            append("Terms of service")
        }
        pop()
        append(" ")
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                color = Gray,
                fontSize = 16.sp
            )
        ) {
            append("&")
        }
        append(" ")
        pushStringAnnotation(tag = "privacy_policy", annotation = "")
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                color = Orange,
                fontSize = 17.sp
            )
        ) {
            append("Privacy policy")
        }
        pop()
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(tag = "terms_of_service", start = offset, end = offset)
                .firstOrNull()
                ?.let {
                    Log.d("TAG", "terms")
                }
            annotatedString
                .getStringAnnotations(tag = "privacy_policy", start = offset, end = offset)
                .firstOrNull()
                ?.let {
                    Log.d("TAG", "privacy")
                }
        },
        style = TextStyle(
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun LogInButton(
    modifier: Modifier = Modifier,
    navigateToAuthenticationScreen: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(60.dp),
        onClick = {
            navigateToAuthenticationScreen()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        elevation = null
    ) {
        Text(
            text = "Log in",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun LogoCircle(
    modifier: Modifier = Modifier,
    isAnimating: Boolean = false
) {
    val offsetX by animateFloatAsState(
        targetValue = if (isAnimating) 100f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    Canvas(modifier = modifier) {
        val center = this.center
        drawCircle(
            color = Color.Black,
            radius = 200f,
            center = Offset(center.x - offsetX, center.y),
            style = Stroke(
                width = 10f
            )
        )
        drawCircle(
            color = Color.Black,
            radius = 200f,
            center = Offset(center.x + offsetX, center.y),
            style = Stroke(
                width = 10f
            )
        )
    }
}

@Preview
@Composable
fun OnBoardingFinishPreview() {
    MemorikTheme {
        OnBoardingFinish { }
    }
}