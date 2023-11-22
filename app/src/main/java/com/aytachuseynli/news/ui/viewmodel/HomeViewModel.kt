package com.aytachuseynli.news.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytachuseynli.news.data.entity.Article
import com.aytachuseynli.news.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _articleList = MutableStateFlow<List<Article>>(emptyList())
    val articleList: StateFlow<List<Article>> get() = _articleList

    private val _searchList = MutableStateFlow<List<Article>>(emptyList())
    val searchList: StateFlow<List<Article>> get() = _searchList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    init {
        fetchTopHeadlines()
    }

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            try {
                println("REQUEST CALLED")
                val response = repository.getTopHeadlines("en")
                println("RESULT ${response.body()}")
                if (response.isSuccessful) {
                    _articleList.value = response.body()?.articles ?: emptyList()
                } else {
                }
            } catch (e: Exception) {
                Log.e("ERROR FETCHING", "fetchTopHeadlines:$e")
            }
        }
    }



}