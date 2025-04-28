package me.dwidar.aflamy.core.model.casts

import me.dwidar.aflamy.core.model.common.BaseModel

data class CastListModel(
    val id: Int,
    val castList: List<CastMemberModel>,
    val crewList: List<CastMemberModel>,
): BaseModel()
