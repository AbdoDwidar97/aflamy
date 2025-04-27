package me.dwidar.aflamy.core.repo.movies_repo

import me.dwidar.aflamy.core.model.common.ListResultModel
import me.dwidar.aflamy.core.model.movies.MovieDetailsModel
import me.dwidar.aflamy.core.model.movies.MovieModel

interface MoviesRepo
{
    suspend fun getPopularMovies(): Result<ListResultModel<MovieModel>>
    suspend fun getSimilarMovies(movieId: Int): Result<ListResultModel<MovieModel>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsModel>
    suspend fun getMoviesResult(query: String)
}