package com.moya.funch.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.moya.funch.icon.FunchIconAsset

@Composable
fun jobPainter(value: String): Painter = when (value) {
    "개발자" -> painterResource(id = FunchIconAsset.Job.developer_24)
    "디자이너" -> painterResource(id = FunchIconAsset.Job.designer_24)
    else -> throw IllegalArgumentException("Unknown Icon: $value")
}

@Composable
fun clubPainter(value: String): Painter = when (value) {
    "넥스터즈" -> painterResource(id = FunchIconAsset.Club.nexters_24)
    "SOPT" -> painterResource(id = FunchIconAsset.Club.sopt_24)
    "Depromeet" -> painterResource(id = FunchIconAsset.Club.depromeet_24)
    else -> throw IllegalArgumentException("Unknown Icon: $value")
}

@Composable
fun subwayLinePainter(value: String): Painter = when (value) {
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
    "BUNDANG" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_suinbundang)
    "AIRPORT" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_airport)
    "YOUNGIN" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_youngin_ever)
    "GYEONGCHUN" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gyeongchun)
    "SILLIM" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_sillim)
    "GYEONGGANG" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_geonggang)
    "SEOHAE" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_seohae)
    "GYEONGUI" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gyeongui_jungang)
    "INCHEON" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_incheon_one)
    "UIJEONGBU" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_uijeongbu)
    "UI_SINSEOL" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_ui_sinseol)
    "GIMPO" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_gimpo_goldline)
    "INCHEON_TWO" -> painterResource(id = FunchIconAsset.SubwayLine.subway_line_incheon_two)
    else -> throw IllegalArgumentException("Unknown Icon: $value")
}
