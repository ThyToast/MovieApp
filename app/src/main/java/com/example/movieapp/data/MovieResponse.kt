package com.example.movieapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object MovieResponse {
    @JsonClass(generateAdapter = true)
    data class DiscoverMovie(
        @field:Json(name = "page")
        val page: Int,
        @field:Json(name = "results")
        val results: ArrayList<MovieResult>,
    )

    data class MovieResult(
        @field:Json(name = "poster_path")
        val posterPath: String?,
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "title")
        val title: String,
        @field:Json(name = "popularity")
        val popularity: Float
    )

    @JsonClass(generateAdapter = true)
    data class MovieDetail(
        @field:Json(name = "poster_path")
        val posterPath: String?,
        @field:Json(name = "overview")
        val overview: String,
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "title")
        val title: String,
        @field:Json(name = "popularity")
        val popularity: Float,
        @field:Json(name = "original_language")
        val originalLanguage: String,
        @field:Json(name = "genres")
        val genres: String,
        @field:Json(name = "runtime")
        val runtime: String,
    )

    data class MovieGenre(
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "name")
        val name: String,
    )
}
