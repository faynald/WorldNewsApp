package com.farhanrv.worldnews.data.source.remote

import com.farhanrv.worldnews.data.source.remote.network.ApiResponse
import com.farhanrv.worldnews.data.source.remote.network.ApiService
import com.farhanrv.worldnews.data.source.remote.response.NewsListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSrouce(private val apiService: ApiService) {
    suspend fun getNews(query: String, key: String, sort: String?): Flow<ApiResponse<List<NewsListResponse>>> {
        return flow {
            try {
                val response = apiService.getNews(query, key, sort)
                if (response.articles.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles.filter { it.content != null && it.title != null && it.description != null }))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCountryNews(category: String?, country: String, key: String): Flow<ApiResponse<List<NewsListResponse>>> {
        return flow {
            try {
                val response = apiService.getCountryNews(category, country, key)
                if (response.articles.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles.filter { it.content != null && it.title != null && it.description != null}))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}