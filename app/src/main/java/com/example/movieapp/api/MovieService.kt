package com.example.movieapp.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    //TODO: Add GET requests

    @GET("/discover/movie")
    suspend fun getMovieList(
        @Query("sort_by") sortBy: String? = ""
    )

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: String)
}