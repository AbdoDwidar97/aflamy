package me.dwidar.aflamy.shell.network.response.movies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.dwidar.aflamy.core.configs.baseCardImageURL
import me.dwidar.aflamy.core.model.movies.MovieDetailsModel
import me.dwidar.aflamy.shell.network.response.common.BaseResponse

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    @Json(name = "adult") val adult: Boolean? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "budget") val budget: Int? = null,
    @Json(name = "homepage") val homepage: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "imdb_id") val imdbId: String? = null,
    @Json(name = "origin_country") val originCountry: List<String>? = null,
    @Json(name = "original_language") val originalLanguage: String? = null,
    @Json(name = "original_title") val originalTitle: String? = null,
    @Json(name = "overview") val overview: String? = null,
    @Json(name = "popularity") val popularity: Double? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "revenue") val revenue: Double? = null,
    @Json(name = "runtime") val runtime: Int? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "tagline") val tagline: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "video") val video: Boolean? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Int? = null
) : BaseResponse {

    override fun convertToModel(): MovieDetailsModel {
        return MovieDetailsModel(
            adult = adult ?: false,
            backdropPath = backdropPath.orEmpty(),
            id = id ?: -1,
            originalLanguage = originalLanguage.orEmpty(),
            originalTitle = originalTitle.orEmpty(),
            overview = overview.orEmpty(),
            popularity = popularity ?: 0.0,
            posterPath = (baseCardImageURL + (posterPath.orEmpty())),
            releaseDate = releaseDate.orEmpty(),
            title = title.orEmpty(),
            video = video ?: false,
            voteAverage = voteAverage ?: 0.0,
            voteCount = voteCount ?: 0,
            budget = budget ?: 0,
            homepage = homepage.orEmpty(),
            imdbId = imdbId.orEmpty(),
            originCountry = originCountry ?: listOf(),
            revenue = revenue ?: 0.0,
            runtime = runtime ?: 0,
            status = status.orEmpty(),
            tagline = tagline.orEmpty()
        )
    }
}
