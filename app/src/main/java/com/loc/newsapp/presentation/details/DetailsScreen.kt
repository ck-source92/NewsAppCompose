package com.loc.newsapp.presentation.details

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.Dimension.ArticleImageHeigth
import com.loc.newsapp.presentation.Dimension.MediumPadding1
import com.loc.newsapp.presentation.details.components.DetailsTopAppBar
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    articles: ArticlesItem,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopAppBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(articles.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, articles.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(
                    DetailsEvent.UpsertDeleteArticle(articles = articles)
                )
            }, onBackClick = navigateUp
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(
                top = MediumPadding1, start =
                MediumPadding1, end = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(articles.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeigth)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = articles.title.toString(), style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title,
                    ),

                    )
                Text(
                    text = articles.content.toString(), style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview() {
    NewsAppTheme(dynamicColor = false) {
        DetailsScreen(
            articles = ArticlesItem(
                publishedAt = "2 Hours",
                author = "Dwi Candra Kirana",
                urlToImage = "https://media.wired.com/photos/65668f0cb38d7a2373721a48/191:100/w_1280,c_limit/Crpyto-Can't-Help-Itself-Business-1400047284.jpg",
                description = "After crashes, scandals, and SBF’s guilty verdict, many hoped the crypto industry would grow up. Speculation around the arrival of a spot bitcoin ETF shows old hype dies hard.",
                source = Source(name = "BDD", id = "1"),
                title = "Lorem Ipsum digunakan sebagai alat untuk mempertaruhkan hal",
                url = "https://www.wired.com/story/bitcoin-etf-crypto-investments/",
                content = "The prospect that US residents may soon be able to invest in bitcoin through their brokerage, as if it were a regular stock, has prompted a fresh round of hype in crypto circlesand a surge in crypto … [+2137 chars]"
            ), event = {}
        ) {

        }
    }
}