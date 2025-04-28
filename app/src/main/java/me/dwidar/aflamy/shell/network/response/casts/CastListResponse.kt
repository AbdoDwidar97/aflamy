package me.dwidar.aflamy.shell.network.response.casts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.dwidar.aflamy.core.model.casts.CastListModel
import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.shell.network.response.common.BaseResponse

@JsonClass(generateAdapter = true)
data class CastListResponse(
    @Json(name = "id") val id: Int?,
    @Json(name = "cast") val castList: List<CastMemberResponse>?,
    @Json(name = "crew") val crewList: List<CastMemberResponse>?
): BaseResponse {
    override fun convertToModel(): CastListModel {

        val castsModel: MutableList<CastMemberModel> = mutableListOf()
        val crewModel: MutableList<CastMemberModel> = mutableListOf()

        castList?.forEach { castItemResponse ->
            castsModel.add(castItemResponse.convertToModel())
        }

        crewList?.forEach { castItemResponse ->
            crewModel.add(castItemResponse.convertToModel())
        }

        return CastListModel(
            id = id ?: -1,
            castList = castsModel,
            crewList = crewModel
        )
    }
}