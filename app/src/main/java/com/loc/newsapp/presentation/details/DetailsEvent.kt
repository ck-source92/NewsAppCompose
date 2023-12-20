package com.loc.newsapp.presentation.details

import com.loc.newsapp.domain.model.ArticlesItem

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val articles: ArticlesItem) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}