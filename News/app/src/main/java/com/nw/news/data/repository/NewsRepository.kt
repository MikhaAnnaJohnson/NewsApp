package com.nw.news.data.repository

import com.nw.news.data.api.NewsApiService

class NewsRepository(private val apiService: NewsApiService) {
    suspend fun getTopHeadlines() = apiService.getTopHeadlines()
}
