package com.nw.news.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nw.news.data.local.dao.ArticleDao
import com.nw.news.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).fallbackToDestructiveMigration()
         //   .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideDao(db: NewsDatabase): ArticleDao = db.articleDao()


//    val MIGRATION_1_2 = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//
//            database.execSQL("ALTER TABLE saved_articles ADD COLUMN source TEXT DEFAULT 'default_value'")
//        }
//    }



}
