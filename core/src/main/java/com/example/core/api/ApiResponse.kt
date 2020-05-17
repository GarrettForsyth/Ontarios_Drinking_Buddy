package com.example.core.api

import android.content.res.Resources
import com.example.core.R
import retrofit2.Response
import java.io.IOException

/**
 * Class used to handle API responses.
 * @param <T> the type of response objects.
 */
sealed class ApiResponse<T> {

    companion object {

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            val message = if (error is IOException) {
                Resources.getSystem().getString(R.string.internet_unavailable)
            } else {
                Resources.getSystem().getString(R.string.unknown_network_error)
            }
            return ApiErrorResponse(message)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                ApiErrorResponse(Resources.getSystem().getString(R.string.unknown_network_error))
            }
        }
    }
}

/**
 * Represents HTTP 204 response
 * Use this to avoid passing a null body with a ApiSuccessResponse
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()