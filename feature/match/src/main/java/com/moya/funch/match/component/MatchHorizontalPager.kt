package com.moya.funch.match.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.entity.match.Matching
import com.moya.funch.entity.match.Recommend
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
    ) { page ->
        MatchCardLayout {
            MatchPageIndicator(current = page, pageCount = pageCount)
            if (page == 0) SimilarityCardContent(similarity, chemistrys)
            if (page == 1) RecommendCardContent(recommends)
        }
    }
}

@Preview(
    showBackground = true,
    name = "MatchHorizontalPager - 640dpi",
    device = "spec:width = 360dp, height = 640dp, dpi = 420",
    showSystemUi = true
)
@Preview(
    showBackground = true,
    name = "MatchHorizontalPager - 800dpi",
    device = "spec:width = 360dp, height = 800dp, dpi = 420",
    showSystemUi = true
)
@Composable
private fun Preview() {
    FunchTheme {
        MatchHorizontalPager(
            matching = Matching(
                recommends = listOf(
                    Recommend("어느 팀이세요?"),
                    Recommend("팀에서 어떤 서비스를 만드나요?"),
                    Recommend("가장 인상 깊었던 여행지는?"),
                    Recommend("가장 좋아하는 음식은?")
                ),
                chemistrys = listOf(
                    Chemistry(
                        title = "찾았다, 내 소울메이트!",
                        description = "ENTJ인 {userName}님은 비전을 향해 적극적으로 이끄는 리더 타입!"
                    ),
                    Chemistry(
                        title = "서로 다른 점을 찾는 재미",
                        description = "B형인 {userName}님은 호기심과 창의력을 갖췄지만 변덕스러워요"
                    ),
                    Chemistry(
                        title = "n호선에서 만나요",
                        description = "{userName}님도 n호선에 살고 있어요"
                    )
                )
            )
        )
    }
}
