package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _discoverMovieRes = MutableLiveData<MovieResponse.DiscoverMovie>()
    val discoverMovieRes: LiveData<MovieResponse.DiscoverMovie> by this::_discoverMovieRes

    fun getDiscoverMovieData(sortBy: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.discoverMovie(sortBy)
            if (response.isSuccessful) {
                _discoverMovieRes.postValue(response.body())
            }
        }
    }
}