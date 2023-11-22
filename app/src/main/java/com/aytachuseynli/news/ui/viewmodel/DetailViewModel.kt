package com.aytachuseynli.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aytachuseynli.news.data.entity.Article
import com.aytachuseynli.news.data.repo.NewsRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {


}