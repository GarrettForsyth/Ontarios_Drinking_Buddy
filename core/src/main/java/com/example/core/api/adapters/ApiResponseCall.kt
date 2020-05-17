package com.example.core.api.adapters

import com.example.core.api.ApiErrorResponse
import com.example.core.api.ApiResponse
import com.example.core.api.ApiSuccessResponse
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCall<T>(proxy: Call<T>) : CallDelegate<T, ApiResponse<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<ApiResponse<T>>) =
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                val result: ApiResponse<T> = if (code in 200 until 300) {
                    val body = response.body()
                    ApiSuccessResponse(body!!)
                } else {
                    ApiErrorResponse("Http error: $code")
                }
                callback.onResponse(this@ApiResponseCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val result: ApiErrorResponse<T> = ApiResponse.create(throwable)
                callback.onResponse(this@ApiResponseCall, Response.success(result))
            }
        })

    override fun cloneImpl() = ApiResponseCall(proxy.clone())
    override fun timeout(): Timeout = Timeout.NONE
}