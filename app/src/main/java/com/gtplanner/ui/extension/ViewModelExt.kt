package com.gtplanner.ui.extension

import androidx.lifecycle.LifecycleCoroutineScope
import com.gtplanner.usecase.utils.tryTo
import com.gtplanner.viewmodel.mvi.MVIIntent
import com.gtplanner.viewmodel.mvi.MVIViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

fun <I : MVIIntent> MVIViewModel<*, I>.runIntentInScope(
    scope: LifecycleCoroutineScope?,
    intent: I
) {
    scope?.launch(Dispatchers.IO) {
        val exception = tryTo { intents.send(intent) }
        Timber.e(exception)
    }
}