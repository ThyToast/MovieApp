package com.example.movieapp.presentation.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : Activity() {
    private lateinit var binding: T

    abstract val bindingInflater: (LayoutInflater) -> T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
    }
}