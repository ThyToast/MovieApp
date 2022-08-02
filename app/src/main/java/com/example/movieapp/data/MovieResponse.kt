package com.example.movieapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object MovieResponse {
    @JsonClass(generateAdapter = true)
    data class DiscoverMovie(
        @field:Json(name = "page")
        val _page: Int?,
        @field:Json(name = "results")
        val _results: List<MovieResult>?,
    ) {
        val page = _page ?: 0
        val results: List<MovieResult> = _results ?: emptyList()
    }

    @JsonClass(generateAdapter = true)
    data class MovieResult(
        @field:Json(name = "poster_path")
        val _posterPath: String?,
        @field:Json(name = "id")
        val _id: Int?,
        @field:Json(name = "title")
        val _title: String?,
        @field:Json(name = "popularity")
        val _popularity: Float?
    ) {
        val posterPath = _posterPath ?: ""
        val id = _id ?: 0
        val title = _title ?: ""
        val popularity = _popularity ?: 0f
    }

    @JsonClass(generateAdapter = true)
    data class MovieDetail(
        @field:Json(name = "poster_path")
        val _posterPath: String?,
        @field:Json(name = "overview")
        val _overview: String?,
        @field:Json(name = "id")
        val _id: Int?,
        @field:Json(name = "title")
        val _title: String?,
        @field:Json(name = "popularity")
        val _popularity: Float?,
        @field:Json(name = "original_language")
        val _originalLanguage: String?,
        @field:Json(name = "genres")
        val _genres: List<MovieGenre>?,
        @field:Json(name = "runtime")
        val _runtime: String?,
    ) {
        val posterPath: String = _posterPath ?: ""
        val overview: String = _overview ?: ""
        val id: Int = _id ?: 0
        val title: String = _title ?: ""
        val popularity: Float = _popularity ?: 0f
        val originalLanguage: String = _originalLanguage ?: ""
        val genres: List<MovieGenre> = _genres ?: emptyList()
        val runtime: String = _runtime ?: ""
    }

    @JsonClass(generateAdapter = true)
    data class MovieGenre(
        @field:Json(name = "id")
        val _id: Int?,
        @field:Json(name = "name")
        val _name: String?,
    ) {
        val id: Int = _id ?: 0
        val name: String = _name ?: ""
    }
}
