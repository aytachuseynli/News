package com.aytachuseynli.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aytachuseynli.news.data.entity.Article
import com.aytachuseynli.news.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val savedArticles: LiveData<List<Article>> = newsRepository.getSavedArticles()


    suspend fun saveArticle(article: Article) {
        withContext(Dispatchers.IO) {
            newsRepository.saveArticle(article)
        }
    }

//    fun isArticleSaved(articleId: Article): Boolean {
//        return newsRepository.isArticleSaved(articleId)
//    }

//    suspend fun deleteArticle(article: Article) {
//        withContext(Dispatchers.IO) {
//            newsRepository.deleteArticle(article)
//        }
//    }
}
