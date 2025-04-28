package me.dwidar.aflamy.shell.network.response.common

import me.dwidar.aflamy.core.model.common.BaseModel

interface BaseResponse {
    fun convertToModel(): BaseModel
}