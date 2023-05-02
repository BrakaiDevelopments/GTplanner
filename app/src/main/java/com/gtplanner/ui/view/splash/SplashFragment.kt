package com.gtplanner.ui.view.splash

import androidx.fragment.app.viewModels
import com.gtplanner.R
import com.gtplanner.databinding.FragmentSplashBinding
import com.gtplanner.ui.extension.dataBindings
import com.gtplanner.ui.standard.fragment.StandardFragment
import com.gtplanner.viewmodel.mvi.MVIState
import com.gtplanner.viewmodel.splash.SplashState
import com.gtplanner.viewmodel.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : StandardFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val binding by dataBindings(FragmentSplashBinding::bind)
    override val viewModel: SplashViewModel by viewModels()

    override fun onViewReady() {

    }

    override fun handleState(state: MVIState) {
        when(state){
            is SplashState.OnDataReady -> handleDataReady(state)
        }
    }

    private fun handleDataReady(splashState: SplashState.OnDataReady) {

    }

}