package com.farhanrv.worldnews.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val author: String?,
    val title: String?,
    val description: String,
    val url: String,
    val image: String?,
    val publishDate: String,
    val content: String,
) : Parcelable
