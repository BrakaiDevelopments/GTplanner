package com.gtplanner.viewmodel.splash

import com.gtplanner.viewmodel.mvi.MVIIntent

sealed class SplashIntent : MVIIntent {

    object Refresh : SplashIntent()

    object OnDataReady : SplashIntent()
}
