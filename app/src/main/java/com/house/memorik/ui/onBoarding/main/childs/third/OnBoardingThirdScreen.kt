package com.house.memorik.ui.onBoarding.main.childs.third

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.house.memorik.ui.onBoarding.main.OnBoardingMainViewModel
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainEvent
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState
import kotlinx.coroutines.delay

@Composable
fun OnBoardingThirdScreen(viewModel: OnBoardingMainViewModel) {
    val state = viewModel.state.observeAsState()
    when (val currentState = state.value) {
        is OnBoardingMainState.Display -> OnBoardingThirdView(state = currentState)
        else -> {}
    }
    LaunchedEffect(key1 = true) {
        delay(300)
        viewModel.obtainEvent(OnBoardingMainEvent.OnBoardingThirdScreenPassed)
    }
}