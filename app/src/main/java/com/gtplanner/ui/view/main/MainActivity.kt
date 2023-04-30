package com.gtplanner.ui.view.main

import androidx.activity.viewModels
import com.gtplanner.databinding.ActivityMainBinding
import com.gtplanner.ui.extension.dataBindings
import com.gtplanner.ui.standard.activity.StandardActivity
import com.gtplanner.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : StandardActivity<ActivityMainBinding, MainViewModel>(){

    override val binding by dataBindings(ActivityMainBinding::inflate)
    override val viewModel: MainViewModel by viewModels()
}