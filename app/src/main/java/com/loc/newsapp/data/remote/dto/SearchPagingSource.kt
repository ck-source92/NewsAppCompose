package com.loc.newsapp.data.remote.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.ArticlesItem

class SearchPagingSource(
    private val searchQuery: String,
    private val newsApi: ApiService,
    private val sources: String
) : PagingSource<Int, ArticlesItem>() {

    private var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val page = params.key ?: 1
        return try {
            val newsResponse =
                newsApi.searchNews(query = searchQuery, page = page, sources = sources)
            newsResponse.articles?.let {
                totalNewsCount += it.size
                val distinctArticles = newsResponse.articles.distinctBy { it.title }
                LoadResult.Page(
                    data = distinctArticles,
                    prevKey = null,
                    nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1
                )
            } ?: LoadResult.Error(Exception("Articles are null"))
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}