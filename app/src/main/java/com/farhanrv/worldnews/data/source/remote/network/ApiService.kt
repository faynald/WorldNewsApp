package com.farhanrv.worldnews.data.source.remote.network

import com.farhanrv.worldnews.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String, // TODO : for search function, use this
        @Query("apiKey") key: String,
        @Query("sortBy") sortBy: String?
    ): NewsResponse

    @GET("v2/top-headlines")
    suspend fun getCountryNews(
        @Query("category") category: String?,
        @Query("country") country: String,
        @Query("apiKey") key: String,
    ): NewsResponse

//    @GET("v2/everything")
//    fun getNewsByCategory( // for chips
//        @Query("q") query: String,
//        @Query("apiKey") key: String,
//        @Query("sortBy") sort: String?
//    ): NewsResponse

}