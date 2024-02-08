package com.moya.funch.modifier

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * @see <a href="https://stackoverflow.com/questions/75035946/how-to-add-scrollbars-to-column">스크롤바 레퍼런스</a>
 */
@Immutable
data class ScrollBarConfig(
    val indicatorHeight: Dp = 39.dp,
    val indicatorThickness: Dp = 8.dp,
    val indicatorColor: Color = Color.LightGray,
    val alpha: Float? = null,
    val alphaAnimationSpec: AnimationSpec<Float>? = null,
    val padding: PaddingValues = PaddingValues(all = 0.dp)
)

fun Modifier.scrollbar(
    state: ScrollState,
    indicatorHeight: Dp = 39.dp,
    indicatorThickness: Dp = 8.dp,
    indicatorColor: Color = Color.LightGray,
    alpha: Float = if (state.isScrollInProgress) 0.8f else 0f,
    alphaAnimationSpec: AnimationSpec<Float> = tween(
        delayMillis = if (state.isScrollInProgress) 0 else 1500,
        durationMillis = if (state.isScrollInProgress) 150 else 500
    ),
    padding: PaddingValues = PaddingValues(all = 0.dp)
): Modifier = composed {
    val scrollbarAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = alphaAnimationSpec,
        label = ""
    )

    drawWithContent {
        drawContent()

        val showScrollBar = state.isScrollInProgress || scrollbarAlpha > 0.0f

        if (showScrollBar) {
            val (topPadding, bottomPadding, startPadding, endPadding) = listOf(
                padding.calculateTopPadding().toPx(),
                padding.calculateBottomPadding().toPx(),
                padding.calculateStartPadding(layoutDirection).toPx(),
                padding.calculateEndPadding(layoutDirection).toPx()
            )

            val viewPortLength = size.height
            val viewPortCrossAxisLength = size.width
            val contentLength = max(viewPortLength + state.maxValue, 0.001f)
            val indicatorThicknessPx = indicatorThickness.toPx()
            val scrollbarSizeWithoutInsets = Size(indicatorThicknessPx, indicatorHeight.toPx())
            val maxScrollOffset = viewPortLength - scrollbarSizeWithoutInsets.height - topPadding - bottomPadding
            val scrollOffsetViewPort =
                if (contentLength > viewPortLength) {
                    topPadding + (state.value / (contentLength - viewPortLength)) * maxScrollOffset
                } else {
                    topPadding
                }

            drawRoundRect(
                color = indicatorColor,
                cornerRadius = CornerRadius(
                    x = indicatorThicknessPx / 2,
                    y = indicatorThicknessPx / 2
                ),
                topLeft = Offset(
                    x = if (layoutDirection == LayoutDirection.Ltr) {
                        viewPortCrossAxisLength - indicatorThicknessPx - endPadding
                    } else {
                        startPadding
                    },
                    y = scrollOffsetViewPort
                ),
                size = scrollbarSizeWithoutInsets,
                alpha = scrollbarAlpha
            )
        }
    }
}

fun Modifier.verticalScrollWithScrollbar(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
    scrollbarConfig: ScrollBarConfig = ScrollBarConfig()
) = this
    .scrollbar(
        state = state,
        indicatorHeight = scrollbarConfig.indicatorHeight,
        indicatorThickness = scrollbarConfig.indicatorThickness,
        indicatorColor = scrollbarConfig.indicatorColor,
        alpha = scrollbarConfig.alpha ?: if (state.isScrollInProgress) 0.8f else 0f,
        alphaAnimationSpec = scrollbarConfig.alphaAnimationSpec ?: tween(
            delayMillis = if (state.isScrollInProgress) 0 else 1500,
            durationMillis = if (state.isScrollInProgress) 150 else 500
        ),
        padding = scrollbarConfig.padding
    )
    .verticalScroll(state, enabled, flingBehavior, reverseScrolling)
