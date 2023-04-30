package com.gtplanner.ui.mvi

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.gtplanner.ui.base.fragment.BaseFragment
import com.gtplanner.viewmodel.standard.StandardViewModel

abstract class MVIFragment<B: ViewDataBinding, V : StandardViewModel<*, *>>(
    @LayoutRes contentLayoutId: Int
) : BaseFragment(contentLayoutId), MVIView<B, V>