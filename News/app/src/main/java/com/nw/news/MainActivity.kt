package com.nw.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nw.news.ui.theme.NewsTheme
import com.nw.news.viewmodel.NewsViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.nw.news.data.local.entity.ArticleEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

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
                            val articleId = backStackEntry.arguments?.getString("articleId")
                            val article = newsViewModel.getArticleById(articleId) // Fetch the article using ID
                            NewsDetailScreen(navController, article)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel) {

    val articles = viewModel.articles.value
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        itemsIndexed(articles) { index, article ->

            NewsListItem(article = article, navController = navController,index)
        }
    }
}

@Composable
fun NewsListItem(article: ArticleEntity, navController: NavController, index: Int) {
    // Create a Column to vertically arrange the components
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { // Making the whole tile clickable
                navController.navigate("newsDetail/${index}")
            }
            .wrapContentHeight(),
    ) {
        // Display the article image
        val painter = rememberImagePainter(
            data = article.imageUrl ?: "https://via.placeholder.com/150"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth() // Make row take full width
                .padding(start=16.dp,top = 8.dp,end=16.dp), // Add padding to Row
            verticalAlignment = Alignment.CenterVertically // Vertically center the content in Row
        ){
            // Profile Icon (Circular Icon) to the left of the title
            val imageModifier = Modifier
                .size(24.dp) // Set the size of the circular icon
                .clip(CircleShape) // Clip the image into a circular shape
                .border(2.dp, Color.White, CircleShape) // Add border around the circular icon
                .padding(2.dp) // Padding for icon size

            Image(
                painter = rememberImagePainter("https://via.placeholder.com/150"),
                contentDescription = "Profile Icon",
                modifier = imageModifier
            )

            // Title Text to the right of the icon
            Text(
                text = article.source,
                modifier = Modifier
                    .weight(1f) // Make the title take up remaining space in the row
                    .padding(start=8.dp,top = 8.dp,end=16.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }

        // Image Section
        val painter1 = rememberImagePainter(
            data = article.imageUrl ?: "https://via.placeholder.com/150"
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(200.dp)
        )

        // Title Text
        Text(
            text = article.title,

            modifier = Modifier
                .fillMaxWidth()
                .padding(start=16.dp,top = 8.dp,end=16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )

        // Description Text
        Text(
            text = article.description ?: "No description available.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start=16.dp,top = 4.dp,end=16.dp),
            fontSize = 14.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

//        OutlinedButton(
//            onClick = {
//                navController.navigate("newsDetail/${index}")
//            },
//            modifier = Modifier.fillMaxWidth(), // Make the button as wide as the parent
//        ) {
//            Text(text = "View Details")
//        }
    }
}

@Composable
fun NewsDetailScreen(navController: NavController, article: ArticleEntity?) {
    if (article != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // Padding for the whole screen
        ) {
            // Title Section
            Text(
                text = "Title: ${article.title}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Description Section
            Text(
                text = "Description: ${article.description}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Content Section
            Text(
                text = "Content: ${article.content}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Image Section
            val painter = rememberImagePainter(
                data = article.imageUrl ?: "https://via.placeholder.com/150"
            )
            Image(
                painter = painter,
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)) // Rounded corners for image
                    .padding(bottom = 16.dp)
            )


        }
    } else {
        // Fallback when article is null
        Text(
            text = "Article not found",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center) // Center the "Article not found" message
                .padding(16.dp)
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NewsTheme {
//        // Placeholder for preview
//        NewsListItem(
//            article = ArticleEntity(title = "Test Title", description = "Test Description", content = "Test Content", imageUrl = "", publishedAt = "",url=""),
//            navController = rememberNavController(),
//            index = 0
//        )
//    }
//}



