package com.moya.funch.match.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.match.theme.Gradient_Lemon500
import com.moya.funch.match.theme.Gray400
import com.moya.funch.match.theme.Gray800
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme

private const val MIN_IMAGE_SIZE = 136
private const val MAX_IMAGE_SIZE = 264
private const val MIN_DEVICE_HEIGHT = 640
private const val MAX_DEVICE_HEIGHT = 800

/**
 * Image 크기를 계산하는 함수
 *
 * 기기가 640 이하인 경우 136dp
 * 800dp 이상인 경우 264dp
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
internal fun SimilarityCardContent(similarity: Int, chemistrys: List<Chemistry>) {
    Spacer(modifier = Modifier.height(8.dp))
    SimilarityText(similarity = similarity)
    Spacer(modifier = Modifier.height(16.dp))
    // @murjune : TODO 나중에 일러스트 나오면 Icon -> Image
    Icon(
        modifier = Modifier
            .background(brush = Brush.horizontalGradient(Gradient_Lemon500))
            .size(imageSize())
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = FunchIconAsset.Etc.close_24),
        tint = Color.Transparent,
        contentDescription = "search"
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
        }
    }
}

@Composable
private fun ChemistryItem(chemistry: Chemistry) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = FunchIconAsset.Search.search_24),
            contentDescription = "search",
            tint = Gray400
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = chemistry.title, style = FunchTheme.typography.sbt2, color = White)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = chemistry.description, style = FunchTheme.typography.b, color = Gray400)
        }
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
            SimilarityCardContent(
                similarity = 0,
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
}
