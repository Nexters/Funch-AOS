package com.moya.funch.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moya.funch.theme.funchTypography

enum class FunchButtonType(val shape: Shape, val contentVerticalPadding: Dp, val textStyle: TextStyle) {
    Full(RoundedCornerShape(16.dp), 21.dp, funchTypography.sbt1),
    Large(RoundedCornerShape(16.dp), 21.dp, funchTypography.sbt1),
    Medium(RoundedCornerShape(16.dp), 16.dp, funchTypography.sbt2),
    Small(RoundedCornerShape(12.dp), 12.dp, funchTypography.b),
    XSmall(RoundedCornerShape(12.dp), 8.dp, funchTypography.b);
}
