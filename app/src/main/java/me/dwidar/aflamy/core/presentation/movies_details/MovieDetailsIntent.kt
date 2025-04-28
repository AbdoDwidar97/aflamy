package me.dwidar.aflamy.core.presentation.movies_details

sealed class MovieDetailsIntent
{
    data class OnGetMovieDetails(val movieId: Int) : MovieDetailsIntent()
    data class OnGetSimilarMovies(val movieId: Int) : MovieDetailsIntent()
    data class OnGetCasts(val movieId: Int) : MovieDetailsIntent()
}