package com.example.movieapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object MovieResponse {
    @JsonClass(generateAdapter = true)
    data class DiscoverMovie(
        @field:Json(name = "page")
        val page: Int,
        @field:Json(name = "results")
        val results: List<MovieResult>,
    )

    @JsonClass(generateAdapter = true)
    data class MovieResult(
        @field:Json(name = "poster_path")
        val posterPath: String?,
        @field:Json(name = "id")
        val id: Int,
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
        val id: Int,
        @field:Json(name = "title")
        val title: String,
        @field:Json(name = "popularity")
        val popularity: Float,
        @field:Json(name = "original_language")
        val originalLanguage: String,
        @field:Json(name = "genres")
        val genres: List<MovieGenre>,
        @field:Json(name = "runtime")
        val runtime: String,
    )

    @JsonClass(generateAdapter = true)
    data class MovieGenre(
        @field:Json(name = "id")
        val id: Int,
        @field:Json(name = "name")
        val name: String,
    )
}
