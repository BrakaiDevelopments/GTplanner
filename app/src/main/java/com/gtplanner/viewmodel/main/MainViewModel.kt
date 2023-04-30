package com.gtplanner.viewmodel.main

import com.gtplanner.viewmodel.standard.StandardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : StandardViewModel<MainState, MainIntent>()