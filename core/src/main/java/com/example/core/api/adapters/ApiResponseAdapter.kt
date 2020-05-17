package com.example.core.api.adapters

import com.example.core.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResponseAdapter(
    private val type: Type
) : CallAdapter<Type, Call<ApiResponse<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> = ApiResponseCall(call)
}

