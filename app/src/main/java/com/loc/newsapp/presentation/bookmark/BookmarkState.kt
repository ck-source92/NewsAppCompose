package com.loc.newsapp.presentation.bookmark

import com.loc.newsapp.domain.model.ArticlesItem

data class BookmarkState(
    val articlesItem: List<ArticlesItem> = emptyList()
)