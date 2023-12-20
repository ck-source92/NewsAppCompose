package com.loc.newsapp.domain.usecase.news

import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.ArticlesItem

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url: String): ArticlesItem? {
        return newsDao.getArticles(url)
    }
}
