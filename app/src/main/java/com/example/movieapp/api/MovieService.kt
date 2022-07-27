package com.example.movieapp.api

import retrofit2.http.GET

interface MovieService {
    //TODO: Add GET requests

    @GET("/")
    suspend fun getMovie()
}