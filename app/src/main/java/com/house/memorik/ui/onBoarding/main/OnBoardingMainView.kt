package com.house.memorik.ui.onBoarding.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.house.memorik.R
import com.house.memorik.ui.authentication.register.views.AuthenticationButton
import com.house.memorik.ui.authentication.register.views.OutlinedButton
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainEvent
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState
import com.house.memorik.ui.theme.LightGray

@Composable
fun OnBoardingMainView(
    viewModel: OnBoardingMainViewModel,
    state: OnBoardingMainState.Display
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnBoardingDivider(isVisible = state.wasMainScreenAnimated)
        Spacer(modifier = Modifier.height(20.dp))
        ContinueButton(
            isVisible = state.wasMainScreenAnimated,
            onClick = {
                viewModel.obtainEvent(OnBoardingMainEvent.OnBoardingMainContinueButtonClicked)
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        LogInButton(
            isVisible = state.wasMainScreenAnimated,
            onClick = {
                viewModel.obtainEvent(OnBoardingMainEvent.OnBoardingMainLogInButtonClicked)
            }
        )
    }
}

@Composable
fun OnBoardingDivider(isVisible: Boolean = false) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            tween(
                delayMillis = 800
            )
        ) + fadeIn(
            tween(
                delayMillis = 800
            )
        )
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            color = LightGray,
            thickness = 2.dp
        )
    }
}

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            tween(
                delayMillis = 900
            )
        ) + fadeIn(
            tween(
                delayMillis = 900
            )
        )
    ) {
        AuthenticationButton(
            text = stringResource(id = R.string.on_boarding_main_screen_continue),
            modifier = modifier
                .padding(horizontal = 20.dp)
                .height(60.dp),
            onClick = { onClick() }
        )
    }
}

@Composable
fun LogInButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            tween(
                delayMillis = 900
            )
        ) + fadeIn(
            tween(
                delayMillis = 900
            )
        )
    ) {
        OutlinedButton(
            modifier = modifier,
            text = stringResource(id = R.string.on_boarding_main_screen_log_in),
            onClick = { onClick() }
        )
    }
}