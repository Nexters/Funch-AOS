package com.moya.funch.match.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.entity.match.Matching
import com.moya.funch.entity.match.Recommend
import com.moya.funch.entity.profile.Profile
import com.moya.funch.match.model.MatchProfileUiModel
import com.moya.funch.theme.FunchTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MatchHorizontalPager(profile: MatchProfileUiModel, similarity: Int, chemistrys: List<Chemistry>) {
    val pageCount = 3
    val pagerState = rememberPagerState(pageCount = { pageCount })
    HorizontalPager(
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier,
        beyondBoundsPageCount = 2,
        state = pagerState
    ) { page ->
        if (page == 0) SimilarityCard(similarity, chemistrys, page, pageCount)
        if (page == 1) RecommendCard(page, pageCount)
        if (page == 2) MatchProfileCard(profile, page, pageCount)
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
            profile = MatchProfileUiModel.from(
                Matching(
                    profile = Profile().copy(
                        name = "abc",
                        job = Job.DEVELOPER,
                        clubs = listOf(Club.SOPT, Club.NEXTERS),
                        mbti = Mbti.INFP,
                        blood = Blood.A,
                        subways = listOf(
                            SubwayStation("목동역", lines = listOf(SubwayLine.FIVE))
                        )
                    ),
                    similarity = 80,
                    chemistrys = listOf(
                        Chemistry("대한민국 선수분들", "정말 고생 많으셨습니다...")
                    ),
                    recommends = listOf(
                        Recommend("개발자"),
                        Recommend("SOPT"),
                        Recommend("ENFJ"),
                        Recommend("A형"),
                        Recommend("목동역")
                    )
                )
            ),
            similarity = 100,
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
    }
}
