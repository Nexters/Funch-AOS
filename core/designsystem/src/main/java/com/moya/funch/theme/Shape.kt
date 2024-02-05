package com.moya.funch.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object FunchRadiusDefaults {
    val Small = 12.dp
    val Medium = 16.dp
    val Large = 20.dp
}

object FunchShapeDefaults {
    val ExtraSmall = RoundedCornerShape(10.dp)
    val Small = RoundedCornerShape(12.dp)
    val Medium = RoundedCornerShape(16.dp)
    val Large = RoundedCornerShape(20.dp)
    val ExtraLarge = RoundedCornerShape(50.dp)
}

@Immutable
class FunchShapes(
    val extraSmall: CornerBasedShape = FunchShapeDefaults.ExtraSmall,
    val small: CornerBasedShape = FunchShapeDefaults.Small,
    val medium: CornerBasedShape = FunchShapeDefaults.Medium,
    val large: CornerBasedShape = FunchShapeDefaults.Large,
    val extraLarge: CornerBasedShape = FunchShapeDefaults.ExtraLarge,
) {
    fun copy(
        extraSmall: CornerBasedShape = this.extraSmall,
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.medium,
        large: CornerBasedShape = this.large,
        extraLarge: CornerBasedShape = this.extraLarge,
    ): FunchShapes {
        return FunchShapes(
            extraSmall = extraSmall,
            small = small,
            medium = medium,
            large = large,
            extraLarge = extraLarge,
        )
    }
}

val LocalFunchShapes = staticCompositionLocalOf { FunchShapes() }
