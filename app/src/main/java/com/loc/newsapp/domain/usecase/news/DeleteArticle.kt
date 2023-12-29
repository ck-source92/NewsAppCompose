package com.loc.newsapp.domain.usecase.news

import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(articlesItem: ArticlesItem) {
        newsRepository.deleteArticles(articlesItem)
    }
}