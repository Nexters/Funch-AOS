package com.moya.funch.modifier

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.Lemon500
import com.moya.funch.theme.Yellow500

/**
 * Composable에 그림자 효과를 추가하는 Modifier입니다.
 *
 * @param color 그림자의 색상입니다.
 * @param borderRadius 그림자가 적용될 border의 radius
 * @param blurRadius 그림자의 흐림 반경
 * @param offsetY 그림자의 수직 위치 offset
 * @param offsetX 그림자의 수평 위치 offset
 * @param spread 그림자의 퍼지는 정도
 * @param modifier
 *
 * @return Composable에 그림자 효과를 적용하는 Modifier
 */
fun Modifier.neonSign(
    color: Color = Lemon500,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 8.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: PaddingValues = PaddingValues(top = (-2).dp, start = 0.dp, end = 0.dp, bottom = 2.dp),
    modifier: Modifier = Modifier,
): Modifier = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas { canvas ->
            val spreadLeft = spread.calculateLeftPadding(LayoutDirection.Ltr).toPx()
            val spreadRight = spread.calculateRightPadding(LayoutDirection.Ltr).toPx()
            val spreadTop = spread.calculateTopPadding().toPx()
            val spreadBottom = spread.calculateBottomPadding().toPx()

            val left = (0f + offsetX.toPx()) - spreadLeft
            val top = (0f + offsetY.toPx()) - spreadTop
            val right = (size.width + offsetX.toPx()) + spreadRight
            val bottom = (size.height + offsetY.toPx()) + spreadBottom
            val paint = Paint().apply {
                val nativePaint = asFrameworkPaint()
                if (blurRadius != 0.dp) {
                    nativePaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
                }
                nativePaint.color = color.toArgb()
            }

            canvas.drawRoundRect(
                left = left,
                top = top,
                right = right,
                bottom = bottom,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)

@Preview(showBackground = true)
@Composable
private fun ShadowPreview() {
    FunchTheme {
        val brush = Brush.horizontalGradient(listOf(Lemon500, Yellow500))
        Column(
            Modifier
                .size(width = 360.dp, height = 114.dp)
                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(horizontal = 4.dp)
                    .neonSign(
                        color = Lemon500,
                        blurRadius = 20.dp,
                        borderRadius = 16.dp
                    )
                    .size(320.dp, 64.dp)
                    .clip(RoundedCornerShape(size = 16.dp))
                    .background(
                        brush
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Button",
                    style = FunchTheme.typography.sbt1,
                    color = Gray900,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

