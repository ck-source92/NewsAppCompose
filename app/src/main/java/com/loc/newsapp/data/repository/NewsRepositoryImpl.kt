package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.remote.ApiService
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.data.remote.SearchPagingSource
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(private val apiService: ApiService, private val newsDao: NewsDao) :
    NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                NewsPagingSource(
                    apiService = apiService,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchPagingSource(
                    searchQuery,
                    newsApi = apiService,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticles(articlesItem: ArticlesItem) {
        newsDao.upsert(articlesItem)
    }

    override suspend fun deleteArticles(articlesItem: ArticlesItem) {
        newsDao.delete(articlesItem = articlesItem)
    }

    override fun selectArticles(): Flow<List<ArticlesItem>> {
        return newsDao.getListArticles().onEach {
            it.reversed()
        }
    }

    override suspend fun selectArticle(url: String): ArticlesItem? {
        return newsDao.getArticles(url)
    }
}