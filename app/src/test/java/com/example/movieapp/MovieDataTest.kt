package com.example.movieapp

import com.example.movieapp.data.MovieResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieDataTest {

    // null data is a common single point of failure and could lead to crashes or unexpected behavior
    // this unit test is to determine if a default value is applied if the value is null

    private lateinit var nullDiscoverMovie: MovieResponse.DiscoverMovie
    private lateinit var nullMovieDetail: MovieResponse.MovieDetail

    @Before
    fun setUp() {
        nullDiscoverMovie = MovieResponse.DiscoverMovie(null, null)
        nullMovieDetail = MovieResponse.MovieDetail(null, null, null, null, null, null, null, null)
    }

    @Test
    fun checkDefaultValues() {
        assertEquals(nullDiscoverMovie.page, 0)
        assertEquals(nullDiscoverMovie.results, emptyList<MovieResponse.MovieResult>())

        assertEquals(nullMovieDetail.posterPath, "")
        assertEquals(nullMovieDetail.overview, "")
        assertEquals(nullMovieDetail.title, "")
        assertEquals(nullMovieDetail.popularity, 0f)
        assertEquals(nullMovieDetail.originalLanguage, "")
        assertEquals(nullMovieDetail.genres, emptyList<MovieResponse.MovieGenre>())
        assertEquals(nullMovieDetail.runtime, "")
    }

}