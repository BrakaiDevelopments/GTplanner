package com.gtplanner.ui.standard.view

import androidx.databinding.ViewDataBinding
import com.gtplanner.entity.ErrorMessage
import com.gtplanner.ui.mvi.MVIView
import com.gtplanner.viewmodel.mvi.MVIState
import com.gtplanner.viewmodel.standard.StandardViewModel

interface StandardView<B: ViewDataBinding, V : StandardViewModel<*, *>> : MVIView<B, V> {

    fun showError(message: ErrorMessage)

    fun handleState(state: MVIState)
}