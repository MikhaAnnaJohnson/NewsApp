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
     //   @Query("apiKey") apiKey: String = "6c8669b0af2145409f3ad732730ace5f"
    ): Response<NewsResponse>

}
