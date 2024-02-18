package com.moya.funch.match.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.match.MatchViewModel
import com.moya.funch.match.R
import com.moya.funch.match.theme.Gradient_Lemon500
import com.moya.funch.match.theme.Gray400
import com.moya.funch.match.theme.Gray800
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme

private const val MIN_IMAGE_SIZE = 136
private const val MAX_IMAGE_SIZE = 200
private const val MIN_DEVICE_HEIGHT = 640
private const val MAX_DEVICE_HEIGHT = 800

/**
 * Image 크기를 계산하는 함수
 *
 * 기기가 640 이하인 경우 136dp
 * 800dp 이상인 경우 200dp
 * 그 외)
 * Image크기 : MIN_IMAGE_SIZE + (device height - MIN_DEVICE_HEIGHT) * increaseRate
 * increaseRate = (MAX_IMAGE_SIZE - MIN_IMAGE_SIZE) / (MAX_DEVICE_HEIGHT - MIN_DEVICE_HEIGHT) = 0.8
 */

@Composable
@Stable
private fun imageSize(): Dp {
    val configuration = LocalConfiguration.current
    val increaseRate = 0.8
    val heightInDp = configuration.screenHeightDp
    // 만약 화면의 높이가 360 보다 작거나 같으면 136.dp
    return when {
        heightInDp < MIN_DEVICE_HEIGHT -> MIN_IMAGE_SIZE.dp
        heightInDp > MAX_DEVICE_HEIGHT -> MAX_IMAGE_SIZE.dp
        else -> ((MIN_IMAGE_SIZE + (heightInDp - MIN_DEVICE_HEIGHT)) * increaseRate).dp
    }
}

private fun Int.formatNumber(): String {
    return String.format("%02d", this)
}

@Composable
internal fun SimilarityCard(similarity: Int, chemistrys: List<Chemistry>, current: Int, pageCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray800, shape = FunchTheme.shapes.large)
            .padding(top = 20.dp)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatchPageIndicator(current = current, pageCount = pageCount)
        Spacer(modifier = Modifier.height(8.dp))
        SimilarityCardContent(similarity = similarity, chemistrys = chemistrys)
    }
}

@Composable
private fun SimilarityCardContent(similarity: Int, chemistrys: List<Chemistry>) {
    SimilarityText(similarity = similarity)
    Spacer(modifier = Modifier.height(16.dp))
    Image(
        modifier = Modifier
            .size(imageSize())
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = similarity.toSimilarityPainter(),
        contentDescription = "Similarity"
    )
    Spacer(modifier = Modifier.height(16.dp))
    ChemistryList(chemistrys = chemistrys)
}

@Composable
private fun SimilarityText(similarity: Int) {
    val text = "우리는 ${similarity.formatNumber()}% 닮았어요"
    val annotatedString = buildAnnotatedString {
        append(text)
        val start = text.indexOf("$similarity")
        addStyle(
            style = SpanStyle(brush = Brush.horizontalGradient(Gradient_Lemon500)),
            start = start,
            end = start + 3
        )
    }

    Text(
        text = annotatedString,
        style = FunchTheme.typography.t2,
        color = White
    )
}

@Composable
private fun ChemistryList(chemistrys: List<Chemistry>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        chemistrys.forEach { chemistry ->
            ChemistryItem(chemistry = chemistry)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun ChemistryItem(chemistry: Chemistry) {
    val (title, description) = chemistry
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Image(modifier = Modifier.size(24.dp), painter = title.toPainter(), contentDescription = "Chemistry Icon")
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, style = FunchTheme.typography.sbt2, color = White)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = description, style = FunchTheme.typography.b, color = Gray400)
        }
    }
}

@Stable
@Composable
private fun Int.toSimilarityPainter(): Painter {
    return when (this) {
        in 81..100 -> painterResource(id = FunchIconAsset.MatchPercentage.hundred)
        in 61..80 -> painterResource(id = FunchIconAsset.MatchPercentage.eighty)
        in 41..60 -> painterResource(id = FunchIconAsset.MatchPercentage.sixty)
        in 21..40 -> painterResource(id = FunchIconAsset.MatchPercentage.forty)
        else -> painterResource(id = FunchIconAsset.MatchPercentage.twenty)
    }
}

@Stable
@Composable
private fun String.toPainter(): Painter {
    return when {
        this == stringResource(id = R.string.match_mbti_pride_first) -> painterResource(id = FunchIconAsset.MBTI.one)

        this == stringResource(id = R.string.match_mbti_pride_second) -> painterResource(id = FunchIconAsset.MBTI.two)

        this == stringResource(id = R.string.match_mbti_pride_third) -> painterResource(id = FunchIconAsset.MBTI.three)

        this == stringResource(id = R.string.match_mbti_pride_fourth) -> painterResource(id = FunchIconAsset.MBTI.four)

        this == stringResource(id = R.string.match_mbti_pride_fifth) -> painterResource(id = FunchIconAsset.MBTI.five)

        this == stringResource(id = R.string.match_blood_good) -> painterResource(id = FunchIconAsset.Blood.good_32)

        this == stringResource(id = R.string.match_blood_soso) -> painterResource(id = FunchIconAsset.Blood.soso_32)

        this == stringResource(id = R.string.match_blood_bad) -> painterResource(id = FunchIconAsset.Blood.bad_32)

        this == stringResource(id = R.string.match_blood_great) -> painterResource(id = FunchIconAsset.Blood.great_32)

        // @murjune TODO 지하철 호선 관련 DTO 변경 시 수정 : 임시로 Profile 박음
        else -> painterResource(id = FunchIconAsset.Etc.profile_80)
    }
}

@Composable
@Preview(
    showBackground = true,
    name = "Phone - 640dpi",
    device = "spec:width = 360dp, height = 640dp, dpi = 420",
    showSystemUi = true
)
@Preview(
    showBackground = true,
    name = "Phone - 720dpi",
    device = "spec:width = 360dp, height = 720dp, dpi = 420",
    showSystemUi = true
)
@Preview(
    name = "Phone - 891dpi",
    device = "spec:width = 411dp, height = 891dp, dpi = 420",
    showSystemUi = true
)
private fun Preview() {
    FunchTheme {
        Column(
            modifier = Modifier
                .background(Gray800)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimilarityCard(
                similarity = MatchViewModel.MOCK_MATCHING.similarity,
                chemistrys = MatchViewModel.MOCK_MATCHING.chemistrys,
                current = 0,
                pageCount = 3
            )
        }
    }
}
