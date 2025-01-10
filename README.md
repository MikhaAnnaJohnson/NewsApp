# News App
![News](https://github.com/user-attachments/assets/8662606a-1bf4-4d86-84f4-bc8391750959)

A modern Android application to read and view news articles with functionalities like fetching data from an API, saving articles locally, 
and displaying them in a user-friendly UI using Jetpack Compose, Hilt for dependency injection, Retrofit for networking, and Room for local database storage.

## Features
- Fetch top news articles from the API.
- Save articles in a local Room database.
- Display articles with images and text in a list.
- Handle network errors and data persistence.

## Technologies Used
- **Kotlin**: Main programming language.
- **Jetpack Compose**: UI framework for declarative UIs.
- **Hilt**: Dependency injection library.
- **Retrofit**: Networking library for fetching news articles.
- **Room**: Local database for saving articles.
- **Coroutines**: For handling asynchronous operations.
- **Glide**: Image loading library.

## Getting Started

### Prerequisites
- Android Studio 4.2 or higher.
- Gradle 7.0 or higher.

### Steps to Run the App
1. Clone this repository:    
 git clone https://github.com/M/news-app.git


2.# Architecture Overview
The News App follows a **MVVM** (Model-View-ViewModel) architecture to separate concerns and improve testability and scalability.

### Components

1. **Model**: 
   - `ArticleEntity`: Represents the data model for articles stored in Room.
   - `NewsResponse`: Represents the data fetched from the API.
   - `Articles`: Represents individual article details.

2. **ViewModel**:
   - `NewsViewModel`: Manages the UI-related data for the News screen, fetches data from the repository, and stores it in `saved_articles`.

3. **Repository**:
   - `NewsRepository`: Handles the data logic. It fetches data from the API (using Retrofit) and saves it to the local database (using Room). It also provides methods to fetch the saved articles.

4. **UI**:
   - `MainActivity`: The activity that hosts the `NewsList` Composable. This component calls the `NewsViewModel` to fetch and display articles.
   - `NewsListScreen`: Composable function that displays the list of news articles.
   - `NewsListItem`: Composable function that displays each article's detail page.


### Data Flow

1. The `NewsViewModel` makes a call to `NewsRepository` to fetch articles.
2. The repository fetches articles from the remote API using Retrofit or from the local Room database.
3. The articles are returned to the `NewsViewModel`, which updates the UI.
4. The UI (in `MainActivity`) observes the `articles` state and displays the list of articles using Jetpack Compose.
5. On clicking each item a detail page of that specific item can be viewed


