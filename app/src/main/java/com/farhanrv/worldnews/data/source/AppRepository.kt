package com.farhanrv.worldnews.data.source

import com.farhanrv.worldnews.data.NetworkBoundResource
import com.farhanrv.worldnews.data.mapper.CountryNewsMapper
import com.farhanrv.worldnews.data.mapper.NewsMapper
import com.farhanrv.worldnews.data.source.local.LocalDataSource
import com.farhanrv.worldnews.data.source.remote.RemoteDataSrouce
import com.farhanrv.worldnews.data.source.remote.network.ApiResponse
import com.farhanrv.worldnews.data.source.remote.response.NewsListResponse
import com.farhanrv.worldnews.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(
    private val remoteDataSource: RemoteDataSrouce,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {
    fun getNews(query: String, key: String, sort: String?): Flow<AppResource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsListResponse>>() {
            override fun loadFromDB(): Flow<List<News>> =
                localDataSource.getNews().map {
                    NewsMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<News>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<NewsListResponse>>> =
                remoteDataSource.getNews(query, key, sort)

            override suspend fun saveCallResult(data: List<NewsListResponse>) {
                localDataSource.deleteNews()
                val newsList = NewsMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    fun getCountryNews(category: String?, country: String, key: String): Flow<AppResource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsListResponse>>() {
            override fun loadFromDB(): Flow<List<News>> =
                localDataSource.getCountryNews().map {
                    CountryNewsMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<News>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<NewsListResponse>>> =
                remoteDataSource.getCountryNews(category, country, key)

            override suspend fun saveCallResult(data: List<NewsListResponse>) {
                localDataSource.deleteCountryNews()
                val newsList = CountryNewsMapper.mapResponsesToEntities(data)
                localDataSource.insertCountryNews(newsList)
            }
        }.asFlow()
}