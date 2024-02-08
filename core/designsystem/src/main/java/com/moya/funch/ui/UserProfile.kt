package com.moya.funch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moya.funch.component.FunchChip
import com.moya.funch.designsystem.R
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.White

data class Profile( // @Gun Hyung TODO : 도메인의 Entity로 변경
    val id: String,
    val code: String,
    val name: String,
    val job: String,
    val clubs: List<String>,
    val mbti: String,
    val blood: String,
    val subwayInfo: SubwayInfo,
) {
    companion object {
        fun default() = Profile(
            id = "QW2E213EEADF",
            code = "U23C", // uppercase는 도메인에서 처리
            name = "김민수",
            job = "개발",
            clubs = listOf("넥스터즈", "SOPT", "Depromeet",),
            mbti = "ENFP",
            blood = "A형",
            subwayInfo = SubwayInfo(
                subwayName = "동대문역사문화공원",
                lines = listOf("1", "4"),
            )
        )
    }
}

data class SubwayInfo( // @Gun Hyung TODO : 도메인의 Entity로 변경
    val subwayName: String,
    val lines: List<String>,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UsersDistinct(
    profile: Profile,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        val labels = mapOf(
            "직군" to listOf(profile.job),
            "동아리" to profile.clubs,
            "MBTI" to listOf(profile.mbti),
            "혈액형" to listOf(profile.blood),
            "지하철" to listOf(profile.subwayInfo.subwayName),
        )

        for (labelName in labels.keys) {
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
                        text = labelName,
                        color = Gray400,
                        style = FunchTheme.typography.b,
                    )
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    labels[labelName]?.let { components ->
                        for (component in components) {
                            FunchChip(
                                leadingIcon = {
                                    when (labelName) {
                                        "직군", "동아리" -> {
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
                                                    painter = profileLeadingIcon(component),
                                                    contentDescription = "",
                                                )
                                            }
                                        }
                                    }
                                },
                                selected = true,
                                enabled = false,
                                label = {
                                    Text(
                                        text = component,
                                        style = FunchTheme.typography.b,
                                        color = White,
                                    )
                                },
                                trailingIcon =
                                if (labelName == "지하철") {
                                    {
                                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                            profile.subwayInfo.lines.forEach { line ->
                                                Image(
                                                    painter = subwayLineIcon(line),
                                                    contentDescription = ""
                                                )
                                            }
                                        }
                                    }
                                } else {
                                    null
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
        "개발" -> painterResource(id = FunchIconAsset.Job.developer_24)
        "디자인" -> painterResource(id = FunchIconAsset.Job.designer_24)
        "넥스터즈" -> painterResource(id = FunchIconAsset.Club.nexters_24)
        "SOPT" -> painterResource(id = FunchIconAsset.Club.sopt_24)
        "Depromeet" -> painterResource(id = FunchIconAsset.Club.depromeet_24)
        else -> throw IllegalArgumentException("Unknown job: $value")
    }

@Composable
private fun subwayLineIcon(line: String): Painter =
    when (line) {
        "1" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_one)
        "2" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_two)
        "3" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_three)
        "4" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_four)
        "5" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_five)
        "6" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_six)
        "7" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_seven)
        "8" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_eight)
        "9" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_nine)
        "경의중앙" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gyeongui_jungang)
        "신분당" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_shinbundang)
        "수인분당" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_suinbundang)
        "공항" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_airport)
        "인천1" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_incheon_one)
        "의정부" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_uijeongbu)
        "우이신설" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_ui_sinseol)
        "김포골드라인" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gimpo_goldline)
        "인천2" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_incheon_two)
        "용인에버라인" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_youngin_ever)
        "신림" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_sillim)
        "경춘" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gyeongchun)
        "경강" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_geonggang)
        "서해" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_seohae)
        else -> throw IllegalArgumentException("Unknown subway line: $line")
    }
