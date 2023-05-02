package com.gtplanner.viewmodel.splash

import com.gtplanner.viewmodel.mvi.MVIState

sealed class SplashState() : MVIState {

    class OnDataReady() : SplashState()
}
