package com.nw.news.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nw.news.data.local.dao.ArticleDao
import com.nw.news.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 3)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
