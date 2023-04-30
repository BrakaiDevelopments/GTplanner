package com.gtplanner.ui.standard.adapter

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.gtplanner.ui.extension.runIntentInScope
import com.gtplanner.ui.mvi.MVIHolder
import com.gtplanner.viewmodel.dto.StandardStateListItem
import com.gtplanner.viewmodel.mvi.MVIIntent
import com.gtplanner.viewmodel.mvi.MVIViewModel

abstract class StandardHolder<in I : StandardStateListItem>(
    private val binding: ViewDataBinding
) : MVIHolder<I>(binding){

    fun <I: MVIIntent> MVIViewModel<*, I>.runIntent(intent: I){
        runIntentInScope(binding.root.findViewTreeLifecycleOwner()?.lifecycleScope, intent)
    }
}