package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {

    val news = newsUseCases.getNews(
        sources = listOf("al-jazeera-english, bbc-news")
    ).cachedIn(scope = viewModelScope)
}