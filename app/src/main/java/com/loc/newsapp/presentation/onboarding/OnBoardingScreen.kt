package com.loc.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.loc.newsapp.presentation.onboarding.components.OnBoarding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            // make button different text each page
            // u must using derived state of
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoarding(page = pages[index])
        }
    }
}