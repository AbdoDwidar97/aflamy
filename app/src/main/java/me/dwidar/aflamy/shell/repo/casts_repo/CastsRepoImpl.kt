package me.dwidar.aflamy.shell.repo.casts_repo

import me.dwidar.aflamy.core.model.casts.CastListModel
import me.dwidar.aflamy.core.network.RequestExecutor
import me.dwidar.aflamy.core.repo.casts_repo.CastsRepo
import me.dwidar.aflamy.shell.network.RetrofitInstance
import me.dwidar.aflamy.shell.network.response.casts.CastListResponse

class CastsRepoImpl : CastsRepo {
    override suspend fun getCasts(movieId: Int): Result<CastListModel>
    {
        val requestExecutor = RequestExecutor<CastListResponse>()
        val result = requestExecutor.execute {
            RetrofitInstance.api.getCasts(RetrofitInstance.apiToken, movieId = movieId)
        }

        result.onSuccess {
            return Result.success(it.convertToModel())
        }.onFailure {
            return Result.failure(it)
        }

        return Result.failure(Exception("Something went wrong"))
    }
}