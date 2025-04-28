package me.dwidar.aflamy.shell.network.response.casts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.dwidar.aflamy.core.configs.baseSmallImageURL
import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.core.model.casts.DepartmentType
import me.dwidar.aflamy.shell.network.response.common.BaseResponse

@JsonClass(generateAdapter = true)
data class CastMemberResponse(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "gender") val gender: Int?,
    @Json(name = "id") val id: Int?,
    @Json(name = "known_for_department") val knownForDepartment: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "original_name") val originalName: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "cast_id") val castId: Int?,
    @Json(name = "character") val character: String?,
    @Json(name = "credit_id") val creditId: String?,
    @Json(name = "order") val order: Int?
): BaseResponse {
    override fun convertToModel(): CastMemberModel {
        return CastMemberModel(
            adult = adult ?: false,
            gender = gender ?: 0,
            id = id ?: -1,
            knownForDepartment = if (knownForDepartment != null) {
                try {
                    DepartmentType.valueOf(knownForDepartment)
                } catch (e: IllegalArgumentException) {
                    DepartmentType.UnKnown
                }
            } else DepartmentType.UnKnown,
            name = name ?: "",
            originalName = originalName ?: "",
            popularity = popularity ?: 0.0,
            profilePath = if (profilePath != null) {
                baseSmallImageURL + profilePath
            } else "",
            castId = castId ?: -1,
            character = character ?: "",
            creditId = creditId ?: "",
            order = order ?: 0
        )
    }
}