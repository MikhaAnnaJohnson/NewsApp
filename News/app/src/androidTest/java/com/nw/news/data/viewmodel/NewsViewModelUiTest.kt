package com.nw.news.data.viewmodel

import com.nw.news.NewsListScreen
import com.nw.news.data.local.entity.ArticleEntity
import com.nw.news.data.repository.NewsRepository
import com.nw.news.viewmodel.NewsViewModel
//import dagger.hilt.android.testing.HiltAndroidRule
//import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.MockitoAnnotations

//@HiltAndroidTest
class NewsViewModelUiTest {

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)
//
//    @Mock
//    lateinit var mockRepository: NewsRepository
//
//    private lateinit var viewModel: NewsViewModel
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        hiltRule.inject()
//        viewModel = NewsViewModel(repository = mockRepository)
//    }
//
//    @Test
//    suspend fun testNewsListIsDisplayed() {
//        // Provide mocked data
//        val mockArticles = listOf(
//            ArticleEntity(url = "1", title = "Test Article 1", description = "Test description", content = "Test content", imageUrl = "test_image_url", publishedAt = "2025-01-01", source = "Test Source", author = "Author")
//        )
//        Mockito.`when`(mockRepository.getSavedArticles()).thenReturn(mockArticles)
//
//        composeTestRule.setContent {
//            NewsListScreen(navController = mockNavController, viewModel = viewModel)
//        }
//
//        composeTestRule.onNodeWithText("Test Article 1").assertIsDisplayed()
//    }
}