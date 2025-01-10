package com.nw.news.data.repository

import com.google.gson.Gson
import com.nw.news.data.api.NewsApiService
import com.nw.news.data.local.dao.ArticleDao
import com.nw.news.data.local.entity.ArticleEntity
import com.nw.news.data.model.Articles
import com.nw.news.data.model.NewsResponse
import com.nw.news.data.model.Source
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {

    @Mock
    private lateinit var apiService: NewsApiService

    @Mock
    private lateinit var articleDao: ArticleDao

    private lateinit var repository: NewsRepository



    @Before
    fun setup() {
        repository = NewsRepository(apiService, articleDao)
    }

    @Test
    fun `test fetching and saving articles`() = runBlockingTest {

        val mockArticles = arrayListOf(Articles(url = "1", title = "Title", description = "Description",author="Anjali",content="", publishedAt = "", source = Source("1","News")))
        `when`(apiService.getTopHeadlines("us")).thenReturn(Response.success(NewsResponse(articles = mockArticles)))

        repository.fetchAndSaveArticles()

        verify(articleDao).saveArticle(anyList())  // Ensure the repository calls the DAO to save articles
    }

    @Test
    fun `getSavedArticles should return list of articles`() = runTest {
        val fakeArticles = listOf(
            ArticleEntity(

                url = "https://example.com/article1",
                title = "Title 1",
                description = "Description 1",
                content = "Content 1",
                imageUrl = "https://example.com/image1.png",
                publishedAt = "2023-12-01",
                source = "Source 1",
                author = "Author 1"
            ),
            ArticleEntity(
                url = "https://example.com/article2",
                title = "Title 2",
                description = "Description 2",
                content = "Content 2",
                imageUrl = "https://example.com/image2.png",
                publishedAt = "2023-12-02",
                source = "Source 2",
                author = "Author 2"
            )
        )
        `when`(articleDao.getSavedArticles()).thenReturn(fakeArticles)

        val result = repository.getSavedArticles()

        assertEquals(fakeArticles, result)
        verify(articleDao, times(1)).getSavedArticles()
    }
}
