package me.dwidar.aflamy.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class RequestExecutor<R>
{
    suspend fun execute(requestCall: suspend () -> R): Result<R> {
        return withContext(Dispatchers.IO) {
            try {
                val response = requestCall()
                Result.success(response)
            } catch (e: SocketTimeoutException) {
                Result.failure(Exception("Timeout - Please try again"))
            } catch (e: IOException) {
                Result.failure(Exception("No Internet Connection"))
            } catch (e: HttpException) {
                val code = e.code()
                val message = when (code) {
                    in 400..499 -> "Client Error $code"
                    in 500..599 -> "Server Error $code"
                    else -> "Unknown HTTP Error"
                }
                Result.failure(Exception(message))
            } catch (e: Exception) {
                Result.failure(Exception("Something went wrong"))
            }
        }
    }
}