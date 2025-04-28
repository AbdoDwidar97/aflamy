package me.dwidar.aflamy.shell.network.response.movies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.dwidar.aflamy.core.configs.baseCardImageURL
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.network.response.common.BaseResponse

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "adult") val adult: Boolean? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "genre_ids") val genreIds: List<Int>? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "original_language") val originalLanguage: String? = null,
    @Json(name = "original_title") val originalTitle: String? = null,
    @Json(name = "overview") val overview: String? = null,
    @Json(name = "popularity") val popularity: Double? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "video") val video: Boolean? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Int? = null
): BaseResponse
{
    override fun convertToModel(): MovieModel {
        return MovieModel(
            adult = adult ?: false,
            backdropPath = backdropPath ?: "",
            genreIds = genreIds ?: listOf(),
            id = id ?: -1,
            originalLanguage = originalLanguage ?: "",
            originalTitle = originalTitle ?: "",
            overview = overview ?: "",
            popularity = popularity ?: 0.0,
            posterPath = if (posterPath != null) {
                baseCardImageURL + posterPath
            } else "",
            releaseDate = releaseDate ?: "",
            title = title ?: "",
            video = video ?: false,
            voteAverage = voteAverage ?: 0.0,
            voteCount = voteCount ?: 0
        )
    }

}