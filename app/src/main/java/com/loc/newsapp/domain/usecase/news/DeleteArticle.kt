package com.loc.newsapp.domain.usecase.news

import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.ArticlesItem

class DeleteArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(articlesItem: ArticlesItem) {
        newsDao.delete(articlesItem)
    }
}