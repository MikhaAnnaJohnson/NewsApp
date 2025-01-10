package com.nw.news.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String,
    val publishedAt: String,
    val source:String,
    val author:String
)


