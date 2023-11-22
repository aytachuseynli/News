package com.aytachuseynli.news.data.repo

import com.aytachuseynli.news.data.entity.NewsResponse
import com.aytachuseynli.news.data.remote.retrofit.NewsDao
import retrofit2.Response
import javax.inject.Inject
import androidx.lifecycle.LiveData
import com.aytachuseynli.news.data.entity.Article
import com.aytachuseynli.news.data.local.ArticleDao


class NewsRepository @Inject constructor(
    private val newsApiService: NewsDao,
    private val articleDao: ArticleDao
) {

    suspend fun getTopHeadlines(country: String): Response<NewsResponse> {
        return newsApiService.getTopHeadlines()
    }

    suspend fun searchNews(lang: String, query: String): Response<NewsResponse> {
        return newsApiService.getTopHeadlines()
    }

    suspend fun saveArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }

//    fun isArticleSaved(articleId: Article): Boolean {
//        return articleDao.isArticleSaved(articleId)
//    }

    fun getSavedArticles(): LiveData<List<Article>> {
        return articleDao.getAllSavedArticles()
    }
}
