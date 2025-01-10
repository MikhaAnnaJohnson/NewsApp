package com.nw.news.data.api

import com.nw.news.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: NewsApiService by lazy {
        instance.create(NewsApiService::class.java)  // Create the instance of NewsApiService
    }
}

