package com.aytachuseynli.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aytachuseynli.news.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class LanguageViewModel @Inject constructor(private val repository: NewsRepository)  : ViewModel() {


}