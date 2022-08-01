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
class DetailsViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _movieDetailRes = MutableLiveData<MovieResponse.MovieDetail>()
    val movieDetailRes: LiveData<MovieResponse.MovieDetail> by this::_movieDetailRes

    fun getMovieDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovieDetail(id)
            if (response.isSuccessful) {
                _movieDetailRes.postValue(response.body())
            }
        }
    }

}