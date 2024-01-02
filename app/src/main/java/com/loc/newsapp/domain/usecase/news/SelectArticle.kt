package com.loc.newsapp.domain.usecase.news

import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): ArticlesItem? {
        return newsRepository.selectArticle(url)
    }
}
