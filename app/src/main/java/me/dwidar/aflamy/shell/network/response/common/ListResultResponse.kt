package me.dwidar.aflamy.shell.network.response.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.dwidar.aflamy.core.model.common.ListResultModel

@JsonClass(generateAdapter = true)
data class ListResultResponse<R, M> (
    @Json(name = "results") val results: List<R>?,
    @Json(name = "page") val resultPage: Int? = null,
    @Json(name = "total_pages") val resultTotalPages: Int? = null,
    @Json(name = "total_results") val resultTotalResults: Int? = null
): BaseResponse {
    override fun convertToModel(): ListResultModel<M> {

        val resultListModel: MutableList<M> = mutableListOf()

        return ListResultModel(
            totalResults = resultTotalResults ?: 0,
            page = resultPage ?: 0,
            totalPages = resultTotalPages ?: 0,
            results = resultListModel
        )
    }
}