package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.ApiService
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.data.remote.SearchPagingSource
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val apiService: ApiService) : NewsRepository {
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
}