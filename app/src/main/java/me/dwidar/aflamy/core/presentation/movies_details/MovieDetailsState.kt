package me.dwidar.aflamy.core.presentation.movies_details

import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.core.model.movies.MovieDetailsModel
import me.dwidar.aflamy.core.model.movies.MovieModel

data class MovieDetailsState(
    val numberOfRequests: Int = 0,
    val movieDetailsModel: MovieDetailsModel = MovieDetailsModel(),
    val similarMovies: List<MovieModel> = mutableListOf(),
    val actorsCast: List<CastMemberModel> = mutableListOf(),
    val directorsCast: List<CastMemberModel> = mutableListOf()
)