package com.nw.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nw.news.data.model.Articles
import com.nw.news.ui.theme.NewsTheme
import com.nw.news.viewmodel.NewsViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import androidx.compose.material3.Button
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.nw.news.data.api.RetrofitClient
import com.nw.news.data.repository.NewsRepository
import com.nw.news.viewmodel.NewsViewModelFactory

// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Create ViewModel instance
            val newsViewModel: NewsViewModel = viewModel(
                factory = NewsViewModelFactory(NewsRepository(RetrofitClient.apiService))  // Pass the Repository here
            )

            NewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "newsList") {
                        composable("newsList") {
                            NewsListScreen(navController = navController, viewModel = newsViewModel)
                        }
                        composable(
                            "newsDetail/{articleId}",
                            arguments = listOf(navArgument("articleId") { type = NavType.StringType })
                        ) { backStackEntry ->
//                            val articleId = backStackEntry.arguments?.getString("articleId")
//                            val article = newsViewModel.getArticleById(articleId) // Fetch the article using ID
//                            NewsDetailScreen(navController, article)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel) {
    val articles = viewModel.news.value

    LazyColumn {
        items(articles) { article ->
            NewsListItem(article = article, navController = navController)
        }
    }
}

@Composable
fun NewsListItem(article: Articles, navController: NavController) {
    Column {
        // Display the article image
        val painter = rememberImagePainter(
            data = article.urlToImage ?: "https://via.placeholder.com/150"
        )
        Image(painter = painter, contentDescription = null, modifier = Modifier.fillMaxSize())

        // Display title and description
        Text(text = article.title)
        Text(text = article.description ?: "No description available.")
        Spacer(modifier = Modifier.height(8.dp))

        // Navigate to the detail page
        Button(onClick = { navController.navigate("newsDetail/${article.url}") }) {
            Text(text = "View Details")
        }
    }
}

@Composable
fun NewsDetailScreen(navController: NavController, article: Articles?) {
    if (article != null) {
        Column {
            Text(text = "Title: ${article.title}")
            Text(text = "Description: ${article.description}")
            Text(text = "Content: ${article.content}")

            val painter = rememberImagePainter(
                data = article.urlToImage ?: "https://via.placeholder.com/150"
            )
            Image(painter = painter, contentDescription = null)

            // Go back button
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
        }
    } else {
        Text(text = "Article not found")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsTheme {
        // Placeholder for preview
        NewsListItem(article = Articles(title = "Test Title", description = "Test Description", content = "Test Content", urlToImage = null), navController = rememberNavController())
    }
}



