package com.moya.funch

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moya.funch.component.FunchChip
import com.moya.funch.entity.profile.Profile
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.White
import com.moya.funch.uimodel.ProfileLabel

@Composable
internal fun MyProfileRoute(
    viewModel: MyProfileViewModel = hiltViewModel(),
    onCloseMyProfile: () -> Unit,
) {
    val profile = viewModel.profile.collectAsState().value

    MyProfileScreen(
        onCloseMyProfile = onCloseMyProfile,
        profile = profile
    )
}

@Composable
internal fun MyProfileScreen(
    onCloseMyProfile: () -> Unit,
    profile: Profile,
) {
    Box(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 14.dp,
                start = 20.dp,
                end = 20.dp,
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(FunchTheme.shapes.large)
                .background(
                    color = Gray800,
                    shape = FunchTheme.shapes.large
                )
                .padding(
                    vertical = 24.dp,
                    horizontal = 20.dp
                )
        ) {
            Text(
                text = profile.code,
                style = FunchTheme.typography.b,
                color = Gray400,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = profile.name,
                style = FunchTheme.typography.t2,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            UsersDistinct(profile = profile)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun UsersDistinct(profile: Profile) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ProfileLabel.entries.forEach { profileLabel ->
            val labelValues = when (profileLabel) {
                ProfileLabel.JOB -> listOf(profile.job.krName)
                ProfileLabel.CLUB -> profile.clubs.map { it.label }
                ProfileLabel.MBTI -> listOf(profile.mbti.name)
                ProfileLabel.BLOOD_TYPE -> listOf(profile.blood.type)
                ProfileLabel.SUBWAY -> profile.subways.map { it.name }
            }

            if (labelValues.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier
                            .width(52.dp)
                            .height(48.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = profileLabel.labelName,
                            color = Gray400,
                            style = FunchTheme.typography.b,
                        )
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        labelValues.forEach { value ->
                            val leadingIcon = when (profileLabel) {
                                ProfileLabel.JOB, ProfileLabel.CLUB ->
                                    profileLeadingIcon(value)

                                else -> null
                            }
                            val trailingIcon = when (profileLabel) {
                                ProfileLabel.SUBWAY ->
                                    profile.subways.find { it.name == value }?.lines?.map {
                                        subwayLineIcon(it.name)
                                    }

                                else -> null
                            }

                            FunchChip(
                                leadingIcon = leadingIcon?.let { icon ->
                                    {
                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Gray900,
                                                    shape = FunchTheme.shapes.extraSmall
                                                )
                                                .clip(FunchTheme.shapes.extraSmall),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                modifier = Modifier.size(18.dp),
                                                painter = icon,
                                                contentDescription = "",
                                            )
                                        }
                                    }
                                },
                                selected = true,
                                enabled = false,
                                label = {
                                    Text(
                                        text = value,
                                        style = FunchTheme.typography.b,
                                        color = White
                                    )
                                },
                                trailingIcon = trailingIcon?.let { trailingIcons ->
                                    {
                                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                            trailingIcons.forEach { icon ->
                                                Image(
                                                    painter = icon,
                                                    contentDescription = ""
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun profileLeadingIcon(value: String): Painter =
    when (value) {
        "개발자" -> painterResource(id = FunchIconAsset.Job.developer_24)
        "디자인" -> painterResource(id = FunchIconAsset.Job.designer_24)
        "디자이너" -> painterResource(id = FunchIconAsset.Club.nexters_24)
        "넥스터즈" -> painterResource(id = FunchIconAsset.Club.nexters_24)
        "SOPT" -> painterResource(id = FunchIconAsset.Club.sopt_24)
        "Depromeet" -> painterResource(id = FunchIconAsset.Club.depromeet_24)
        else -> throw IllegalArgumentException("Unknown job: $value")
    }

@Composable
private fun subwayLineIcon(line: String): Painter = // @Gun Hyung TODO : 신림역부터 도메인 Entity 추가 되는데로 수정
    when (line) {
        "ONE" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_one)
        "TWO" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_two)
        "THREE" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_three)
        "FOUR" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_four)
        "FIVE" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_five)
        "SIX" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_six)
        "SEVEN" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_seven)
        "EIGHT" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_eight)
        "NINE" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_nine)
        "SINBUNDANG" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_shinbundang)
        "SUIN" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_suinbundang)
        "AIRPORT" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_airport)
        "EVERLINE" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_youngin_ever)
        "GYEONGCHUN" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gyeongchun)
        "신림" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_sillim)
        "경강" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_geonggang)
        "서해" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_seohae)
        "경의중앙" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gyeongui_jungang)
        "인천1" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_incheon_one)
        "의정부" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_uijeongbu)
        "우이신설" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_ui_sinseol)
        "김포골드라인" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gimpo_goldline)
        "인천2" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_incheon_two)
        else -> throw IllegalArgumentException("Unknown subway line: $line")
    }

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
)
@Composable
private fun Preview1() {
    FunchTheme {
        MyProfileScreen(
            onCloseMyProfile = {},
            profile = Profile.default()
        )
    }
}
