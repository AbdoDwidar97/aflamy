package me.dwidar.aflamy.core.model.movies

data class MovieModel(
    val adult: Boolean =  false,
    val backdropPath: String? = null,
    val genreIds: List<Int> = mutableListOf(),
    val id: Int = -1,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? = null,
    val releaseDate: String = "2025-03-26",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)
