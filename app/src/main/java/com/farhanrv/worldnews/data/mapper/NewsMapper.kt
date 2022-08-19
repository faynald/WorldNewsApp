package com.farhanrv.worldnews.data.mapper

import com.farhanrv.worldnews.data.source.local.entity.NewsEntity
import com.farhanrv.worldnews.data.source.remote.response.NewsListResponse
import com.farhanrv.worldnews.domain.model.News
import kotlinx.serialization.json.JsonNull.content

object NewsMapper {
    fun mapResponsesToEntities(input: List<NewsListResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                image = it.image,
                publishDate = it.publishDate,
                content = it.content
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> {
        val newsList = ArrayList<News>()
        input.map {
            val news = News(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                image = it.image,
                publishDate = it.publishDate,
                content = it.content
            )
            newsList.add(news)
        }
        return newsList
    }
}