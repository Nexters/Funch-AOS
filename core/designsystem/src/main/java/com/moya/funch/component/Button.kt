package com.moya.funch.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.modifier.clickableSingle
import com.moya.funch.modifier.neonSign
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.Lemon500
import com.moya.funch.theme.Lemon900
import com.moya.funch.theme.Yellow500
import com.moya.funch.theme.funchTypography

enum class FunchButtonType(val shape: Shape, val contentVerticalPadding: Dp, val textStyle: TextStyle) {
    Full(RoundedCornerShape(16.dp), 21.dp, funchTypography.sbt1),
    Large(RoundedCornerShape(16.dp), 21.dp, funchTypography.sbt1),
    Medium(RoundedCornerShape(16.dp), 16.dp, funchTypography.sbt2),
    Small(RoundedCornerShape(12.dp), 12.dp, funchTypography.b),
    XSmall(RoundedCornerShape(12.dp), 8.dp, funchTypography.b);
}

data class FunchIcon(
    @DrawableRes val resId: Int,
    val description: String,
    val tint: Color,
)

@Composable
fun FunchMainButton(
    buttonType: FunchButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentHorizontalPadding: Dp = 0.dp,
    icon: @Composable () -> Unit = {},
) {
    FunchMainButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = buttonType.shape,
        contentPadding = PaddingValues(contentHorizontalPadding, buttonType.contentVerticalPadding)
    ) {
        Text(text = text, color = Gray900, style = buttonType.textStyle)
        icon()
    }
}

@Composable
fun FunchMainButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(size = 16.dp),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val brush = Brush.horizontalGradient(listOf(Lemon500, Yellow500))
    val disabledColor = Lemon900
    Box(
        modifier = modifier
            .then(
                if (enabled) Modifier.neonSign(
                    color = Lemon500, borderRadius = 16.dp
                ) else Modifier
            )
            .clip(shape = shape)
            .then(
                if (enabled) Modifier.background(brush = brush)
                else Modifier.background(color = disabledColor)
            )
            .clickableSingle(enabled = enabled, onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            content()
        }
    }
}

@Composable
fun FunchSubButton(
    buttonType: FunchButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentHorizontalPadding: Dp = 0.dp,
    icon: @Composable () -> Unit = {},
) {
    val color = if (enabled) {
        FunchTheme.colors.white
    } else {
        Gray400
    }
    FunchSubButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = buttonType.shape,
        contentPadding = PaddingValues(contentHorizontalPadding, buttonType.contentVerticalPadding)
    ) {
        Text(text = text, color = color, style = buttonType.textStyle)
        icon()
    }
}

@Composable
fun FunchSubButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(size = 16.dp),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = shape)
            .background(Gray800)
            .clickableSingle(enabled = enabled, onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            content()
        }
    }
}

@Composable
fun FunchIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    funchIcon: FunchIcon,
    backgroundColor: Color = Color.Transparent,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(0.dp),
    indication: Indication? = LocalIndication.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = roundedCornerShape,
            )
            .clip(roundedCornerShape)
            .clickable(
                onClick = onClick,
                indication = indication,
                interactionSource = interactionSource,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = funchIcon.resId),
            contentDescription = funchIcon.description,
            tint = funchIcon.tint,
        )
    }
}

/*============================== Preview =================================*/

@Preview(name = "main button size", showBackground = true, widthDp = 360)
@Composable
private fun Preview1() {
    FunchTheme {
        Column(
            Modifier
                .background(Gray900)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FunchMainButton(
                modifier = Modifier.fillMaxWidth(),
                buttonType = FunchButtonType.Full,
                onClick = { /*TODO*/ },
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchMainButton(
                buttonType = FunchButtonType.Large,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 120.dp,
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchMainButton(
                buttonType = FunchButtonType.Medium,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 60.dp,
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchMainButton(
                buttonType = FunchButtonType.Small,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 16.dp,
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchMainButton(
                buttonType = FunchButtonType.XSmall,
                onClick = { /*TODO*/ },
                text = "Button",
                contentHorizontalPadding = 12.dp
            )
        }
    }
}

@Preview(name = "subButton - size", showBackground = true, widthDp = 360)
@Composable
private fun Preview2() {
    FunchTheme {
        Column(
            Modifier
                .background(Gray900)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FunchSubButton(
                modifier = Modifier.fillMaxWidth(),
                buttonType = FunchButtonType.Full,
                onClick = { /*TODO*/ },
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchSubButton(
                buttonType = FunchButtonType.Large,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 120.dp,
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchSubButton(
                buttonType = FunchButtonType.Medium,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 60.dp,
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchSubButton(
                buttonType = FunchButtonType.Small,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 16.dp,
                text = "Button"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            FunchSubButton(
                buttonType = FunchButtonType.XSmall,
                onClick = { /*TODO*/ },
                text = "Button",
                contentHorizontalPadding = 12.dp
            )
        }
    }
}

@Preview(name = "main button - enabled, disabled", showBackground = true, widthDp = 360)
@Composable
private fun Preview3() {
    FunchTheme {
        Column(
            Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            FunchMainButton(contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp), onClick = { }) {
                Text(
                    text = "Button",
                    style = FunchTheme.typography.sbt1,
                    color = Gray900,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))

            FunchMainButton(
                enabled = false,
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
                onClick = { }) {
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

@Preview(name = "subButton - enabled, disabled ", showBackground = true, widthDp = 360)
@Composable
private fun Preview4() {
    FunchTheme {
        Column(
            Modifier
                .background(Gray900)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            FunchSubButton(
                buttonType = FunchButtonType.Medium,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 60.dp,
                text = "Button"
            )

            Spacer(modifier = Modifier.padding(16.dp))
            FunchSubButton(
                enabled = false,
                buttonType = FunchButtonType.Medium,
                onClick = { /*TODO*/ },
                contentHorizontalPadding = 60.dp,
                text = "Button"
            )
        }
    }
}

@Preview("Icon Large Button", showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun Preview5() {
    FunchIconButton(
        modifier = Modifier.size(40.dp),
        roundedCornerShape = RoundedCornerShape(12.dp),
        backgroundColor = Gray500,
        onClick = { /*TODO*/ },
        funchIcon = FunchIcon(
            resId = FunchIconAsset.Search.search_24,
            description = "",
            tint = Yellow500,
        ),
    )
}

@Preview("Icon Medium Button", showBackground = true)
@Composable
private fun Preview6() {
    FunchIconButton(
        onClick = { /*TODO*/ },
        funchIcon = FunchIcon(
            resId = FunchIconAsset.Search.search_24,
            description = "",
            tint = Gray400,
        ),
    )
}

@Preview("Icon Small Button", showBackground = true)
@Composable
private fun Preview7() {
    FunchIconButton(
        onClick = { /*TODO*/ },
        funchIcon = FunchIcon(
            resId = FunchIconAsset.Search.search_16,
            description = "",
            tint = Gray400,
        ),
        indication = null,
    )
}
