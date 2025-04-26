package me.dwidar.aflamy.shell.repo.movies_repo

import me.dwidar.aflamy.core.model.common.ListResultModel
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.core.network.RequestExecutor
import me.dwidar.aflamy.core.repo.movies_repo.MoviesRepo
import me.dwidar.aflamy.shell.network.RetrofitInstance
import me.dwidar.aflamy.shell.network.response.common.ListResultResponse
import me.dwidar.aflamy.shell.network.response.movies.MovieResponse

class MoviesRepoImpl : MoviesRepo
{
    override suspend fun getPopularMovies(): Result<ListResultModel<MovieModel>>
    {
        val requestExecutor = RequestExecutor<ListResultResponse<MovieResponse, MovieModel>>()
        val result = requestExecutor.execute {
            RetrofitInstance.api.getPopularMovies(RetrofitInstance.apiToken)
        }

        result.onSuccess {
            return Result.success(it.convertToModel().also { mdl ->
                it.results?.forEach {
                    mdl.results.add(it.convertToModel() as MovieModel)
                }
            })
        }.onFailure {
            return Result.failure(it)
        }

        return Result.failure(Exception("Something went wrong"))
    }

    override suspend fun getMoviesResult(query: String)
    {
        TODO("Not yet implemented")
    }
}