package com.farhanrv.worldnews.data.source.local

import com.farhanrv.worldnews.data.source.local.entity.CountryNewsEntity
import com.farhanrv.worldnews.data.source.local.entity.NewsEntity
import com.farhanrv.worldnews.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val appDao: AppDao) {
    fun getNews(): Flow<List<NewsEntity>> = appDao.getNews()

    suspend fun insertNews(news: List<NewsEntity>) = appDao.insertNews(news)

    suspend fun deleteNews() = appDao.deleteNews()

    fun getCountryNews(): Flow<List<CountryNewsEntity>> = appDao.getCountryNews()

    suspend fun insertCountryNews(news: List<CountryNewsEntity>) = appDao.insertCountryNews(news)

    suspend fun deleteCountryNews() = appDao.deleteCountryNews()

    fun updateNews(news: NewsEntity) = appDao.updateNews(news)

    fun deleteNews(news: NewsEntity) = appDao.deleteNews(news)
}