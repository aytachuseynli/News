package com.aytachuseynli.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aytachuseynli.news.data.entity.Article

class SharedViewModel : ViewModel() {
    private val _savedArticles = MutableLiveData<List<Article>>()
    val savedArticles: LiveData<List<Article>> get() = _savedArticles
    fun updateSavedArticles(newArticles: List<Article>) {
        _savedArticles.value = newArticles
    }

}