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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moya.funch.common.clubPainter
import com.moya.funch.common.jobPainter
import com.moya.funch.common.subwayLinePainter
import com.moya.funch.component.FunchChip
import com.moya.funch.component.FunchIcon
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.theme.White
import com.moya.funch.ui.FunchTopBar
import com.moya.funch.uimodel.ProfileLabel

@Composable
internal fun MyProfileRoute(viewModel: MyProfileViewModel = hiltViewModel(), onCloseMyProfile: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is MyProfileUiState.Loading -> {
            // @Gun Hyung TODO : 추후 로딩 화면 추가
        }
        is MyProfileUiState.Error -> {
            // @Gun Hyung TODO : 추후 에러 화면 추가
        }
        is MyProfileUiState.Success -> {
            MyProfileScreen(
                profile = uiState.profile,
                onCloseMyProfile = onCloseMyProfile
            )
        }
    }
}

@Composable
internal fun MyProfileScreen(profile: Profile, onCloseMyProfile: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FunchTopBar(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 20.dp
                ),
            enabledLeadingIcon = true,
            enabledTrailingIcon = true,
            leadingIcon = FunchIcon(
                resId = FunchIconAsset.Arrow.arrow_left_small_24,
                description = "Back",
                tint = Gray400
            ),
            onClickLeadingIcon = onCloseMyProfile
        )
        Box(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    bottom = 14.dp,
                    start = 20.dp,
                    end = 20.dp
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
                    color = Gray400
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = profile.name,
                    style = FunchTheme.typography.t2,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                UsersDistinct(profile = profile)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun UsersDistinct(profile: Profile) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileLabel.entries.filterNot { it == ProfileLabel.NICKNAME }.forEach { profileLabel ->
            val labelValues = when (profileLabel) {
                ProfileLabel.JOB -> listOf(profile.job.krName)
                ProfileLabel.CLUB -> profile.clubs.map { it.label }
                ProfileLabel.MBTI -> listOf(profile.mbti.name)
                ProfileLabel.BLOOD_TYPE -> listOf(profile.blood.type)
                ProfileLabel.SUBWAY -> profile.subways.map { it.name }
                ProfileLabel.NICKNAME -> emptyList()
            }

            if (labelValues.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
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
                            style = FunchTheme.typography.b
                        )
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        labelValues.forEach { value ->
                            val leadingIcon = when (profileLabel) {
                                ProfileLabel.JOB -> jobPainter(value)
                                ProfileLabel.CLUB -> clubPainter(value)
                                else -> null
                            }
                            val trailingIcon = when (profileLabel) {
                                ProfileLabel.SUBWAY ->
                                    profile.subways.find { it.name == value }?.lines?.map {
                                        subwayLinePainter(it.name)
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
                                                contentDescription = ""
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

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6
)
@Composable
private fun Preview1() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            MyProfileScreen(
                onCloseMyProfile = {},
                profile = Profile(
                    id = "QW2E213EEADF",
                    code = "U23C",
                    name = "김민수",
                    job = Job.DEVELOPER,
                    clubs = listOf(Club.NEXTERS, Club.SOPT, Club.DEPROMEET),
                    mbti = Mbti.ENFP,
                    blood = Blood.A,
                    subways = listOf(
                        SubwayStation(
                            "동대문역사문화공원",
                            listOf(
                                SubwayLine.ONE,
                                SubwayLine.FOUR
                            )
                        ),
                        SubwayStation(
                            "초지역",
                            listOf(
                                SubwayLine.TWO,
                                SubwayLine.THREE
                            )
                        )
                    )
                )
            )
        }
    }
}
