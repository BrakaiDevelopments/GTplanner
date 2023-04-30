package com.gtplanner.viewmodel.mvi

import androidx.lifecycle.LiveData
import com.gtplanner.viewmodel.base.BaseViewModel
import kotlinx.coroutines.channels.Channel

abstract class MVIViewModel<S : MVIState, I : MVIIntent> : BaseViewModel() {
    abstract val intents: Channel<I>
    abstract val state: LiveData<S>
}