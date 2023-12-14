package com.loc.newsapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.loc.newsapp.domain.model.ArticlesItem

data class NewsResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)