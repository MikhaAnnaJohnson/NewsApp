package com.nw.news.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.nw.news.data.local.entity.ArticleEntity
import com.nw.news.viewmodel.BookmarkViewModel

@Composable
fun SaveButton(article: ArticleEntity, viewModel: BookmarkViewModel) {
//    IconButton(onClick = {
//        viewModel.saveArticle(article)
//    }) {
//        Icon(Icons.Filled.Favorite, contentDescription = "Save")
//    }
}
