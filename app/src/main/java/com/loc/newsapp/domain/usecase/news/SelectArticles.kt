package com.loc.newsapp.domain.usecase.news

import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<ArticlesItem>> {
        return newsDao.getListArticles()
    }
}