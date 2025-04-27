package me.dwidar.aflamy.shell.network
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.network.response.casts.CastListResponse
import me.dwidar.aflamy.shell.network.response.common.ListResultResponse
import me.dwidar.aflamy.shell.network.response.movies.MovieDetailsResponse
import me.dwidar.aflamy.shell.network.response.movies.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface APIService
{
    @GET("movie/popular")
    suspend fun getPopularMovies(@Header("Authorization") token: String): ListResultResponse<MovieResponse, MovieModel>

    @GET("movie/{id}/similar")
    suspend fun getSimilarMovies(@Header("Authorization") token: String, @Path("id") movieId: Int): ListResultResponse<MovieResponse, MovieModel>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Header("Authorization") token: String, @Path("id") movieId: Int): MovieDetailsResponse

    @GET("movie/{id}/credits")
    suspend fun getCasts(@Header("Authorization") token: String, @Path("id") movieId: Int): CastListResponse
}