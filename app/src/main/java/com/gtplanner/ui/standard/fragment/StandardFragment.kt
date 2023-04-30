package com.gtplanner.ui.standard.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.gtplanner.ui.mvi.MVIFragment
import com.gtplanner.ui.standard.view.StandardView
import com.gtplanner.viewmodel.standard.StandardViewModel
import com.gtplanner.BR
import com.gtplanner.entity.ErrorMessage
import com.gtplanner.ui.extension.runIntentInScope
import com.gtplanner.viewmodel.mvi.MVIIntent
import com.gtplanner.viewmodel.mvi.MVIState
import com.gtplanner.viewmodel.mvi.MVIViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class StandardFragment<B : ViewDataBinding, V: StandardViewModel<*, *>>(
    @LayoutRes contentLayoutId: Int
) : MVIFragment<B, V>(contentLayoutId), StandardView<B, V> {

    override val viewModelVariableId = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(viewModelVariableId, viewModel)
        viewModel.observeState()
        viewModel.observeError()
        onViewReady()
    }

    fun <I : MVIIntent> MVIViewModel<*, I>.runIntent(intent: I){
        runIntentInScope(viewLifecycleOwner.lifecycleScope, intent)
    }

    private fun V.observeState() = state.observe(viewLifecycleOwner) { it?.let { handleState(it) }}

    private fun V.observeError() {
        viewLifecycleOwner.lifecycleScope.launch {
            error.receiveAsFlow().collect{ showError(it) }
        }
    }

    override fun showError(message: ErrorMessage) {
        if (activity is StandardView<*, *>){
            val standardActivity = (activity as StandardView<*, *>)
            standardActivity.viewModel.updateError(message)
        }
    }

    override fun onViewReady() = Unit

    override fun handleState(state: MVIState) = Unit
}