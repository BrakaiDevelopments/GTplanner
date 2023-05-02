package com.gtplanner.viewmodel.splash

import com.gtplanner.viewmodel.standard.StandardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : StandardViewModel<SplashState, SplashIntent>(){

    override suspend fun handleIntent(intent: SplashIntent) = when(intent){
        SplashIntent.OnDataReady -> TODO()
        SplashIntent.Refresh -> TODO()
    }

}