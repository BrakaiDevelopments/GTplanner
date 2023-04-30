package com.gtplanner.ui.mvi

import androidx.databinding.ViewDataBinding
import com.gtplanner.ui.base.activity.BaseActivity
import com.gtplanner.viewmodel.standard.StandardViewModel

abstract class MVIActivity<B : ViewDataBinding, V : StandardViewModel<*, *>> : BaseActivity(), MVIView<B, V>