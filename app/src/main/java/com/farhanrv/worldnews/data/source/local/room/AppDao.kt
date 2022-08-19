package com.farhanrv.worldnews.data.source.local.room

import androidx.room.*
import com.farhanrv.worldnews.data.source.local.entity.CountryNewsEntity
import com.farhanrv.worldnews.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM news_entity")
    fun getNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("DELETE FROM news_entity")
    suspend fun deleteNews()

    @Query("SELECT * FROM country_news_entity")
    fun getCountryNews(): Flow<List<CountryNewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryNews(news: List<CountryNewsEntity>)

    @Query("DELETE FROM country_news_entity")
    suspend fun deleteCountryNews()

    @Update
    fun updateNews(news: NewsEntity)

    @Delete
    fun deleteNews(news: NewsEntity)
}