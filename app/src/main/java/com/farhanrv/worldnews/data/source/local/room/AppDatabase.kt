package com.farhanrv.worldnews.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farhanrv.worldnews.data.source.local.entity.CountryNewsEntity
import com.farhanrv.worldnews.data.source.local.entity.NewsEntity

@Database(entities = [NewsEntity::class, CountryNewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}