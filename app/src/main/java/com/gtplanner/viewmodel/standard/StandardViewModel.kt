package com.gtplanner.viewmodel.standard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gtplanner.entity.ErrorMessage
import com.gtplanner.usecase.base.InputPort
import com.gtplanner.usecase.base.OutputPort
import com.gtplanner.viewmodel.mvi.MVIIntent
import com.gtplanner.viewmodel.mvi.MVIState
import com.gtplanner.viewmodel.mvi.MVIViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@Suppress("MemberVisibilityCanBePrivate")
abstract class StandardViewModel<S : MVIState, I : MVIIntent>(
    private val backgroundDispatcher: CoroutineContext = Dispatchers.IO
) : MVIViewModel<S, I>() {

    override val intents: Channel<I> by lazy {
        Channel<I>().tryToConsume()
    }
    private val _state = MutableLiveData<S>()
    override val state: LiveData<S> = _state
    val error = Channel<ErrorMessage>()

    init {
        tryToInjectOutputPorts()
    }

    private fun Channel<I>.tryToConsume(): Channel<I> {
        launchInIO { tryTo { consumeAsFlow().collect { tryToHandleIntent(it) } } }
        return this@tryToConsume
    }

    private suspend fun tryToHandleIntent(intent: I) = tryTo {
        handleIntent(intent)
    }

    protected open suspend fun handleIntent(intent: I): Any = Unit

    private fun <O : OutputPort> O.tryToInjectOutputPorts() {
        launchInIO { tryTo { injectOutputPorts() } }
    }

    private suspend fun <O : OutputPort> O.injectOutputPorts() = this::class.memberProperties.map {
        it.isAccessible = true
        it.getter.call(this)
    }.filterIsInstance<InputPort<O>>().forEach {
        it.registerOutputPort(this@injectOutputPorts)
    }

    open fun updateError(e: Throwable) = updateError(ErrorMessage.DEFAULT)

    open fun updateError(message: ErrorMessage) {
        launchInIO { error.send(message) }
    }

    protected suspend fun tryTo(callback: suspend () -> Unit) = try {
        callback()
    } catch (e: Throwable) {
        Timber.e(e)
        updateError(e)
    }

    protected fun <T> Flow<T>.collectResult(
        action: (value: T) -> Unit
    ) = launchInIO { tryTo { collect{ tryTo { action(it) } } } }


    protected fun updateState(state: S) = _state.postValue(state)

    protected fun launchInIO(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend  CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context = backgroundDispatcher, start = start, block = block)
}