package com.house.memorik.ui.onBoarding.main.childs.second

import androidx.compose.runtime.Composable
import com.house.memorik.ui.onBoarding.main.OnBoardingMainViewModel
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState

@Composable
fun OnBoardingSecondScreen(viewModel: OnBoardingMainViewModel) {
    val state = viewModel.state
    when (state.value) {
        is OnBoardingMainState.Display -> OnBoardingSecondView()
        else -> {}
    }
}

