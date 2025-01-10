package com.nw.news.data.api

import com.nw.news.BuildConfig
import com.nw.news.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponse>

}
