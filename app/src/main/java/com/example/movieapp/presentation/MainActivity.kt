package com.example.movieapp.presentation

import android.view.LayoutInflater
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
}