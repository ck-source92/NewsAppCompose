package com.loc.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val articles = newsUseCases.selectArticle(event.articles.url)
                    if (articles == null) {
                        upsertArticle(event.articles)
                    } else {
                        deleteArticle(event.articles)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
            else -> {}
        }
    }

    private suspend fun deleteArticle(articles: ArticlesItem) {
        newsUseCases.deleteArticle(articles)
        sideEffect = "Article Deleted"
    }

    private suspend fun upsertArticle(articlesItem: ArticlesItem) {
        newsUseCases.upsertArticles(articlesItem)
        sideEffect = "Article Saved"
    }
}