package com.loc.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.ArticlesItem
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.Dimension.ArticleImageSize
import com.loc.newsapp.presentation.Dimension.ExtraSmallPadding
import com.loc.newsapp.presentation.Dimension.ExtraSmallPadding2
import com.loc.newsapp.presentation.Dimension.SmallIconSize
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier, articles: ArticlesItem, onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(modifier = Modifier.clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleImageSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context = context).data(data = articles.urlToImage)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(ExtraSmallPadding)
                .height(ArticleImageSize)
        ) {
            Text(
                text = articles.title ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = articles.source?.name.toString(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(
                        SmallIconSize
                    ),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = articles.publishedAt.toString(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewArticleCard() {
    NewsAppTheme {
        ArticleCard(
            articles = ArticlesItem(
                publishedAt = "2 Hours",
                author = "Dwi Candra Kirana",
                urlToImage = "",
                description = "Ini Gambar 1",
                source = Source(name = "BDD", id = "1"),
                title = "Lorem Ipsum digunakan sebagai alat untuk mempertaruhkan hal",
                url = "",
                content = ""
            )
        ) {

        }
    }
}