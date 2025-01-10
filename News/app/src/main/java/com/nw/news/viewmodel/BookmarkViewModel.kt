package com.nw.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nw.news.data.local.dao.ArticleDao
import com.nw.news.data.local.entity.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class BookmarkViewModel(private val dao: ArticleDao) : ViewModel() {
//    val savedArticles: StateFlow<List<ArticleEntity>> = dao.getSavedArticles()
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//    fun saveArticle(article: ArticleEntity) {
//        viewModelScope.launch {
//            dao.saveArticle(article)
//        }
//    }
//
//    fun deleteArticle(article: ArticleEntity) {
//        viewModelScope.launch {
//            dao.deleteArticle(article)
//        }
//    }
}
