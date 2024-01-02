package com.loc.newsapp.domain.usecase.news

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String>): Flow<PagingData<ArticlesItem>> {
        return newsRepository.getNews(sources = sources)
    }
}