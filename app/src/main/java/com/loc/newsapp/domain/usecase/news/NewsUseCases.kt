package com.loc.newsapp.domain.usecase.news

import com.loc.newsapp.domain.usecase.news.search.SearchNews

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews
)