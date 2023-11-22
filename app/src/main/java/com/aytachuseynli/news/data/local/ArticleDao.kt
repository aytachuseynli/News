package com.aytachuseynli.news.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.aytachuseynli.news.data.entity.Article

@Dao
interface ArticleDao {
    // ...

    @Upsert
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)
    @Query("SELECT * FROM articles")
    fun getSavedArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%'")
    fun searchArticles(query: String): LiveData<List<Article>>

    @Query("SELECT * FROM articles ORDER BY id DESC")
    fun getAllSavedArticles(): LiveData<List<Article>>

//    @Query("SELECT COUNT(*) FROM articles WHERE id = :articleId")
//    fun isArticleSaved(articleId: Article): Boolean

    // ...
}