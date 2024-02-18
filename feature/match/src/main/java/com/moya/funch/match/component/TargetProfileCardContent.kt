package com.moya.funch.match.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.common.clubPainter
import com.moya.funch.common.jobPainter
import com.moya.funch.common.subwayLinePainter
import com.moya.funch.component.FunchChip
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
import com.moya.funch.match.model.MatchingWrapper
import com.moya.funch.match.theme.Gray400
import com.moya.funch.match.theme.Gray900
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme

@Composable
internal fun TargetProfileCardContent(profile: MatchProfileUiModel) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = profile.name, style = FunchTheme.typography.t2, color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))

        ProfileInfo(
            profile.job, profile.clubs, profile.mbti, profile.blood, profile.subways
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProfileInfo(
    job: MatchingWrapper<Job>,
    clubs: List<MatchingWrapper<Club>>,
    mbti: MatchingWrapper<Mbti>,
    blood: MatchingWrapper<Blood>,
    subways: List<MatchingWrapper<SubwayStation>>
) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // job
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileItemTitle(title = job.profileItem.title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MatchChip(
                    label = job.data.krName,
                    mached = job.matched,
                    leadingPainter = jobPainter(value = job.data.krName)
                )
            }
        }
        // clubs
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val title = clubs.firstOrNull()?.profileItem?.title.orEmpty()
            ProfileItemTitle(title = title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                clubs.forEach { club ->
                    MatchChip(
                        label = club.data.label,
                        mached = club.matched,
                        leadingPainter = clubPainter(value = club.data.label)
                    )
                }
            }
        }
        // mbti
        listOf<Int>().size
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileItemTitle(title = mbti.profileItem.title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MatchChip(
                    label = mbti.data.name,
                    mached = mbti.matched,
                    leadingPainter = null
                )
            }
        }
        // blood
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileItemTitle(title = blood.profileItem.title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MatchChip(
                    label = blood.data.type,
                    mached = blood.matched,
                )
            }
        }
        // subways
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val title = subways.firstOrNull()?.profileItem?.title.orEmpty()
            ProfileItemTitle(title = title)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                subways.forEach { subway ->
                    MatchChip(
                        label = subway.data.name,
                        mached = subway.matched,
                        trailingPainter = subway.data.lines.map {
                            subwayLinePainter(value = it.name)
                        }
                    )
                }
            }
        }
    }
}


@Composable
private fun ProfileItemTitle(title: String) {
    Box(
        modifier = Modifier
            .width(52.dp)
            .height(48.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title, color = Gray400, style = FunchTheme.typography.b
        )
    }
}

@Composable
private fun MatchChip(
    label: String,
    leadingPainter: Painter? = null,
    trailingPainter: List<Painter>? = null,
    mached: Boolean
) {
    FunchChip(
        matched = mached,
        selected = true,
        enabled = false,
        leadingIcon = { LeadingIcon(painter = leadingPainter) },
        label = { ChipLabel(label = label) },
        trailingIcon = { TrailingIcon(painters = trailingPainter) })
}

@Composable
private fun ChipLabel(label: String) {
    Text(
        text = label, style = FunchTheme.typography.b, color = White
    )
}

@Composable
private fun LeadingIcon(painter: Painter? = null) {
    painter?.let {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = Gray900, shape = FunchTheme.shapes.extraSmall
                )
                .clip(FunchTheme.shapes.extraSmall),
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = Modifier.size(18.dp), painter = it, contentDescription = ""
            )

        }
    }
}

@Composable
private fun TrailingIcon(painters: List<Painter>? = null) {
    painters?.let {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            painters.forEach {
                Image(painter = it, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FunchTheme {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)) {
            TargetProfileCardContent(
                MatchProfileUiModel.from(
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
                            Recommend("넥스터즈"),
                            Recommend("ENFJ"),
                            Recommend("A형"),
                            Recommend("목동역"),
                        )
                    )
                )
            )
        }
    }
}
