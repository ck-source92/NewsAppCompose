package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<ArticlesItem>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<ArticlesItem>>
    suspend fun upsertArticles(articlesItem: ArticlesItem)
    suspend fun deleteArticles(articlesItem: ArticlesItem)
    fun selectArticles(): Flow<List<ArticlesItem>>
    suspend fun selectArticle(url: String): ArticlesItem?
}