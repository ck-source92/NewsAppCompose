package com.loc.newsapp.presentation.search

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<ArticlesItem>>? = null
)