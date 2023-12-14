package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    // make image to integer
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy.",
        description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        image = R.drawable.onboarding3
    )
)
