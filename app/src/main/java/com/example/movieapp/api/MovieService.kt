package com.example.movieapp.api

import com.example.movieapp.data.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("sort_by") sortBy: String
    ): Response<MovieResponse.DiscoverMovie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): Response<MovieResponse.MovieDetail>
}