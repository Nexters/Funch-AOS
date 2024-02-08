package com.moya.funch.match.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.entity.match.Matching
import com.moya.funch.theme.FunchTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchHorizontalPager(matching: Matching) {
    val (profile, similarity, chemistrys, recommends, subways) = matching

    val pageCount = 3
    val pagerState = rememberPagerState(pageCount = { pageCount })
    HorizontalPager(
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier,
        beyondBoundsPageCount = 2,
        state = pagerState
    ) {
        MatchCardLayout {
            MatchPageIndicator(current = pagerState.currentPage, pageCount = pageCount)
        }
    }
}

@Preview(showBackground = true, name = "MatchHorizontalPager")
@Composable
private fun Preview() {
    FunchTheme {
        MatchHorizontalPager(
            matching = Matching()
        )
    }
}
