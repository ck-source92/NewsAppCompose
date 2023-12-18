package com.loc.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.presentation.Dimension

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articlesItem: LazyPagingItems<ArticlesItem>,
    onClick: (ArticlesItem) -> Unit
) {
    val handlePagingResult = handlePagingResult(articlesItem = articlesItem)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimension.MediumPadding1),
            contentPadding = PaddingValues(all = Dimension.ExtraSmallPadding2)
        ) {
            items(count = articlesItem.itemCount) { index ->
                articlesItem[index].let { article ->
                    article?.let {
                        ArticleCard(articles = it, onClick = { onClick(article) })
                    }
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(
    articlesItem: LazyPagingItems<ArticlesItem>,
): Boolean {

    val loadState = articlesItem.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimension.MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimension.MediumPadding1)
            )
        }
    }
}