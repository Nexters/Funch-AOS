package com.moya.funch.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Yellow500

@Immutable
data class FunchIcon(
    @DrawableRes val resId: Int,
    val description: String,
    val tint: Color,
)

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

@Preview("Icon Large Button", showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun FunchLargeIconButtonPreview() {
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
fun FunchMediumIconButtonPreview() {
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
fun FunchSmallIconButtonPreview() {
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
