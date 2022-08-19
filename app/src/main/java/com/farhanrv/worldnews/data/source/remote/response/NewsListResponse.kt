package com.farhanrv.worldnews.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("urlToImage")
    val image: String,

    @field:SerializedName("publishedAt")
    val publishDate: String,

    @field:SerializedName("content")
    val content: String,
)