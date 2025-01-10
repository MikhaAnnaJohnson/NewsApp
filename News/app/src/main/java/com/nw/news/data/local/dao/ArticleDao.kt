package com.nw.news.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nw.news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: List<ArticleEntity>)

    @Query("SELECT * FROM saved_articles")
    suspend fun getSavedArticles(): List<ArticleEntity>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)

}
