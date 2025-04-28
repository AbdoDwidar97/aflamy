package me.dwidar.aflamy.core.repo.casts_repo

import me.dwidar.aflamy.core.model.casts.CastListModel

interface CastsRepo
{
    suspend fun getCasts(movieId: Int): Result<CastListModel>
}