package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

}