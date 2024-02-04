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
        "개발" -> painterResource(id = R.drawable.ic_developer_18)
        "디자인" -> painterResource(id = R.drawable.ic_designer_18)
        "넥스터즈" -> painterResource(id = R.drawable.ic_nexters_24)
        "Depromeet" -> painterResource(id = R.drawable.ic_depromeet_24)
        "SOPT" -> painterResource(id = R.drawable.ic_sopt_24)
        else -> throw IllegalArgumentException("Unknown job: $value")
    }

@Composable
private fun subwayLineIcon(line: String): Painter =
    when (line) {
        "1" -> painterResource(id = R.drawable.ic_subway_line_one_16)
        "2" -> painterResource(id = R.drawable.ic_subway_line_two_16)
        "3" -> painterResource(id = R.drawable.ic_subway_line_three_16)
        "4" -> painterResource(id = R.drawable.ic_subway_line_four_16)
        "5" -> painterResource(id = R.drawable.ic_subway_line_five_16)
        "6" -> painterResource(id = R.drawable.ic_subway_line_six_16)
        "7" -> painterResource(id = R.drawable.ic_subway_line_seven_16)
        "8" -> painterResource(id = R.drawable.ic_subway_line_eight_16)
        "9" -> painterResource(id = R.drawable.ic_subway_line_nine_16)
        "경의중앙" -> painterResource(id = R.drawable.ic_subway_line_gyeongui_jungang_16)
        "신분당" -> painterResource(id = R.drawable.ic_subway_line_shinbundang_16)
        "수인분당" -> painterResource(id = R.drawable.ic_subway_line_suinbundang_16)
        "공항철도" -> painterResource(id = R.drawable.ic_subway_line_airport_16)
        "인천1" -> painterResource(id = R.drawable.ic_subway_line_incheon_one_16)
        "의정부" -> painterResource(id = R.drawable.ic_subway_line_uijeongbu_16)
        "우이신설" -> painterResource(id = R.drawable.ic_subway_line_ui_sinseol_16)
        "김포골드" -> painterResource(id = R.drawable.ic_subway_line_gimpo_goldline_16)
        "인천2" -> painterResource(id = R.drawable.ic_subway_line_incheon_two_16)
        "용인에버" -> painterResource(id = R.drawable.ic_subway_line_youngin_ever_16)
        "신림" -> painterResource(id = R.drawable.ic_subway_line_sillim_16)
        "경춘" -> painterResource(id = R.drawable.ic_subway_line_gyeongchun_16)
        "경강" -> painterResource(id = R.drawable.ic_subway_line_geonggang_16)
        "서해선" -> painterResource(id = R.drawable.ic_subway_line_seohae_16)
        else -> throw IllegalArgumentException("Unknown subway line: $line")
    }
