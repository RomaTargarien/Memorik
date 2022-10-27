package com.house.memorik.ui.onBoarding.main.childs.second

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.house.memorik.R
import com.house.memorik.ui.theme.LightGray
import com.house.memorik.ui.theme.MemorikTheme

@Composable
fun OnBoardingSecondView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppImagePreview()
        TitleText()
        Spacer(modifier = Modifier.height(20.dp))
        SubtitleText()
    }
}

@Composable
fun AppImagePreview(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(450.dp)
                .clip(RoundedCornerShape(20.dp)),
            painter = painterResource(id = R.drawable.image_on_boarding_phone),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
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

@Composable
@Preview
fun OnBoardingSecondPreview() {
    MemorikTheme {
        OnBoardingSecondView()
    }
}