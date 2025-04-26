package me.dwidar.aflamy.shell.network
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.network.response.common.ListResultResponse
import me.dwidar.aflamy.shell.network.response.movies.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface APIService
{
    @GET("movie/popular")
    suspend fun getPopularMovies(@Header("Authorization") token: String): ListResultResponse<MovieResponse, MovieModel>
}