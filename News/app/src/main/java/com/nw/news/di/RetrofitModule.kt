package com.nw.news.di

import com.nw.news.data.api.NewsApiService
import com.nw.news.data.api.RetrofitClient
import com.nw.news.data.local.dao.ArticleDao
import com.nw.news.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    private val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewApiService(retrofit: Retrofit): NewsApiService{
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        apiService: NewsApiService,
        dao: ArticleDao
    ): NewsRepository {
        return NewsRepository(apiService, dao)
    }
}