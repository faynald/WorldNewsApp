package com.farhanrv.worldnews.data.mapper

import com.farhanrv.worldnews.data.source.local.entity.CountryNewsEntity
import com.farhanrv.worldnews.data.source.local.entity.NewsEntity
import com.farhanrv.worldnews.data.source.remote.response.NewsListResponse
import com.farhanrv.worldnews.domain.model.News

object CountryNewsMapper {
    fun mapResponsesToEntities(input: List<NewsListResponse>): List<CountryNewsEntity> {
        val newsList = ArrayList<CountryNewsEntity>()
        input.map {
            val news = CountryNewsEntity(
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

    fun mapEntitiesToDomain(input: List<CountryNewsEntity>): List<News> {
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