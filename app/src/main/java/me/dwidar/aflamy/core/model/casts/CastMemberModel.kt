package me.dwidar.aflamy.core.model.casts

import me.dwidar.aflamy.core.model.common.BaseModel

enum class DepartmentType {
    Directing,
    Acting,
    UnKnown
}

data class CastMemberModel(
    val adult: Boolean = false,
    val gender: Int = 2,
    val id: Int = -1,
    val knownForDepartment: DepartmentType = DepartmentType.UnKnown,
    val name: String = "",
    val originalName: String = "",
    val popularity: Double = 0.0,
    val profilePath: String = "",
    val castId: Int = 0,
    val character: String = "",
    val creditId: String = "",
    val order: Int = 0
): BaseModel()
