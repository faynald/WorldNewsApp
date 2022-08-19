package com.farhanrv.worldnews.data

import com.farhanrv.worldnews.data.source.AppResource
import com.farhanrv.worldnews.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<AppResource<ResultType>> = flow {
        emit(AppResource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(AppResource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { AppResource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { AppResource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(AppResource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { AppResource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<AppResource<ResultType>> = result
}