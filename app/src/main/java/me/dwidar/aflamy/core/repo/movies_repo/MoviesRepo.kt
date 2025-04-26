package me.dwidar.aflamy.core.repo.movies_repo

import me.dwidar.aflamy.core.model.common.ListResultModel
import me.dwidar.aflamy.core.model.movies.MovieModel

interface MoviesRepo
{
    suspend fun getPopularMovies(): Result<ListResultModel<MovieModel>>
    suspend fun getMoviesResult(query: String)
}