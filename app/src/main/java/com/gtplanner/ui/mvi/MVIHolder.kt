package com.gtplanner.ui.mvi

import androidx.databinding.ViewDataBinding
import com.gtplanner.ui.base.adapter.BaseHolder
import com.gtplanner.viewmodel.dto.StateListItem

abstract class MVIHolder<in I : StateListItem>(binding: ViewDataBinding) : BaseHolder(binding.root) {

    abstract fun bind(item: I)
}