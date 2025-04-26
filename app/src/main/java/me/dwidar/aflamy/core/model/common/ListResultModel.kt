package me.dwidar.aflamy.core.model.common

open class ListResultModel<M>(
    val page: Int = 0,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
    val results: MutableList<M> = mutableListOf()
): BaseModel()