package me.dwidar.aflamy.core.model.movies

import me.dwidar.aflamy.core.model.common.BaseModel

data class MovieDetailsModel(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val budget: Int = 0,
    val homepage: String = "",
    val id: Int = -1,
    val imdbId: String = "",
    val originCountry: List<String> = listOf(),
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val revenue: Double = 0.0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
): BaseModel()