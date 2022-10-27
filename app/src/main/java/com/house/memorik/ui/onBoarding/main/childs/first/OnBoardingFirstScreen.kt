package com.house.memorik.ui.onBoarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.house.memorik.ui.onBoarding.main.OnBoardingMainViewModel
import com.house.memorik.ui.onBoarding.main.childs.first.OnBoardingFirstView
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainEvent
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState

@Composable
fun OnBoardingFirstScreen(
    viewModel: OnBoardingMainViewModel
) {
    val state = viewModel.state.observeAsState()
    when (val currentState = state.value) {
        is OnBoardingMainState.Display -> OnBoardingFirstView(state = currentState)
        else -> {}
    }
    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(OnBoardingMainEvent.OnBoardingFirstScreenPassed)
    }
}

