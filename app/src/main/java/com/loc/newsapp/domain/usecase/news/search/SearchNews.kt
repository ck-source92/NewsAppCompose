package com.loc.newsapp.domain.usecase.news.search

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(private val newsRepository: NewsRepository) {

    operator fun invoke(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<ArticlesItem>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}