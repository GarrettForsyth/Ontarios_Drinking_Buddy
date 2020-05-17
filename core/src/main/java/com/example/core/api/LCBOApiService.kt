package com.example.core.api

import com.example.core.vo.LCBOItem
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface LCBOApiService {
    @GET("/products")
    suspend fun fetchLcboItems(
        @QueryMap options: Map<String, String>
    ): ApiResponse<List<LCBOItem>>
}