package com.example.movieapp.data

import com.example.movieapp.data.network.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: MovieService) {

    suspend fun discoverMovie(sortBy: String = ""): Response<MovieResponse.DiscoverMovie> {
        return service.getMovieList(sortBy)
    }

    suspend fun getMovieDetail(movieId: Int): Response<MovieResponse.MovieDetail> {
        return service.getMovieDetail(movieId)
    }

}