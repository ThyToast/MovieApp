package com.example.movieapp.data

object MovieSelection {
    val movieFilter: Array<String> = arrayOf(
        "By popularity (Ascending)",
        "By popularity (Descending)",
        "By release date (Ascending)",
        "By release date (Descending)",
        "By alphabetical order (Ascending)",
        "By alphabetical order (Descending)",
    )

    val filterValues: Array<String> = arrayOf(
        "popularity.asc",
        "popularity.desc",
        "release_date.asc",
        "release_date.desc",
        "original_title.asc",
        "original_title.desc",
    )

    const val movieBookingUrl: String = "https://www.cathaycineplexes.com.sg/"
    const val movieImageUrl: String = "https://image.tmdb.org/t/p/original"
}

