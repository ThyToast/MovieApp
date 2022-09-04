package com.example.movieapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.movieapp.data.MovieSelection.movieBookingUrl
import com.example.movieapp.databinding.FragmentBookMovieBinding
import com.example.movieapp.presentation.ui.base.BaseFragment

class BookMovieFragment : BaseFragment<FragmentBookMovieBinding>() {
    private lateinit var webView: WebView
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBookMovieBinding
        get() = FragmentBookMovieBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding.wvMovie
        webView.loadUrl(movieBookingUrl)
    }
}