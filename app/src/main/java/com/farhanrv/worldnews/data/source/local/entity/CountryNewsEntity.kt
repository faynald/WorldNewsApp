package com.farhanrv.worldnews.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "country_news_entity")
data class CountryNewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String?,
    val title: String?,
    val description: String,
    val url: String,
    val image: String?,
    val publishDate: String,
    val content: String,
) : Parcelable
