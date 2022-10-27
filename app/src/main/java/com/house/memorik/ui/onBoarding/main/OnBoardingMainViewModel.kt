package com.house.memorik.ui.onBoarding.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.house.memorik.base.EventHandler
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainEvent
import com.house.memorik.ui.onBoarding.main.models.OnBoardingMainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingMainViewModel @Inject constructor() : ViewModel(), EventHandler<OnBoardingMainEvent> {

    private val _onLoginButtonClicked: MutableSharedFlow<Unit> = MutableSharedFlow()
    val onLoginButtonClicked: MutableSharedFlow<Unit> = _onLoginButtonClicked

    private val _onContinueButtonClicked: MutableSharedFlow<Unit> = MutableSharedFlow()
    val onContinueButtonClicked: MutableSharedFlow<Unit> = _onContinueButtonClicked

    private val _state: MutableLiveData<OnBoardingMainState> = MutableLiveData(initialState())
    val state: LiveData<OnBoardingMainState> = _state

    override fun obtainEvent(event: OnBoardingMainEvent) {
        when (val state = _state.value) {
            is OnBoardingMainState.Display -> reduce(event, state)
            else -> {}
        }
    }

    private fun reduce(event: OnBoardingMainEvent, state: OnBoardingMainState.Display) {
        when (event) {
            is OnBoardingMainEvent.OnBoardingFirstScreenPassed -> onBoardingFirstScreenPassed(state)
            is OnBoardingMainEvent.OnBoardingThirdScreenPassed -> onBoardingThirdScreenPassed(state)
            is OnBoardingMainEvent.OnBoardingMainScreenEntered -> onBoardingMainScreenPassed(state)
            is OnBoardingMainEvent.OnBoardingMainLogInButtonClicked -> onLoginButtonClicked()
            is OnBoardingMainEvent.OnBoardingMainContinueButtonClicked -> onContinueButtonClicked()
        }
    }

    private fun onBoardingMainScreenPassed(state: OnBoardingMainState.Display) {
        val newState = state.copy(wasMainScreenAnimated = true)
        _state.postValue(newState)
    }

    private fun onBoardingFirstScreenPassed(state: OnBoardingMainState.Display) {
        val newState = state.copy(wasFirstScreenAnimated = true)
        _state.postValue(newState)
    }

    private fun onBoardingThirdScreenPassed(state: OnBoardingMainState.Display) {
        val newState = state.copy(wasThirdScreenAnimated = true)
        _state.postValue(newState)
    }

    private fun onLoginButtonClicked() {
        viewModelScope.launch {
            _onLoginButtonClicked.emit(Unit)
        }
    }

    private fun onContinueButtonClicked() {
        viewModelScope.launch {
            _onContinueButtonClicked.emit(Unit)
        }
    }

    private fun initialState(): OnBoardingMainState = OnBoardingMainState.Display()
}