package com.example.core.vo

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.core.api.ApiErrorResponse
import com.example.core.api.ApiResponse
import com.example.core.api.ApiSuccessResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow() = flow<Resource<ResultType>> {
        emit(Resource.loading(null))
        val dbValue = loadFromDb().first()
        if (shouldFetch(dbValue)) {
            emit(Resource.loading(dbValue))
            when (val apiResponse = fetchFromNetwork()) {
                is ApiSuccessResponse -> {
                    saveNetworkResult(processResponse(apiResponse))
                    emitAll(loadFromDb().map { Resource.success(it) })
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    emitAll(loadFromDb().map { Resource.error(apiResponse.errorMessage, it) })
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.success(it) })
        }
    }

    protected open fun onFetchFailed() {
        // Implement in sub-classes to handle errors
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): ApiResponse<RequestType>
}