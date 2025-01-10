package com.nw.news.data.repository

import com.nw.news.data.api.NewsApiService
import com.nw.news.data.local.dao.ArticleDao
import com.nw.news.data.local.entity.ArticleEntity
import com.nw.news.data.model.Articles
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
    private val dao: ArticleDao
) {

    /**
     * Fetches top headlines from the API and saves them to the database.
     */
    suspend fun fetchAndSaveArticles(country: String = "us") {
        try {
            val response = apiService.getTopHeadlines(country)
            if (response.isSuccessful) {
                val articles = response.body()?.articles.orEmpty()
                var id=0
                val articleEntities = articles.map { article ->
                    ArticleEntity(
                        url = id++.toString(),
                        title = article.title,
                        description = article.description ?: "",
                        content = article.content ?: "",
                        imageUrl = article.urlToImage ?: "",
                        publishedAt = article.publishedAt ?: "",
                        source = article.source?.name ?:"",
                        author = article.author?:""
                    )
                }
                dao.saveArticle(articleEntities)
            } else {
                throw Exception("API Error: ${response.message()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    /**
     * Retrieves saved articles from the local database.
     */
    suspend fun getSavedArticles(): List<ArticleEntity> = dao.getSavedArticles()


}
