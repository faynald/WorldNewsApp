package com.farhanrv.worldnews.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("totalResults")
    val total: Int,

    @field:SerializedName("articles")
    val articles: List<NewsListResponse>,
)