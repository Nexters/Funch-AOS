package com.moya.funch.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Yellow500

data class IconType(
    @DrawableRes val resId: Int,
    val description: String,
    val tint: Color,
)

@Composable
fun FunchIconLargeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconType: IconType,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .padding(8.dp)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = interactionSource,
            ),
    ) {
        Icon(
            painter = painterResource(id = iconType.resId),
            contentDescription = iconType.description,
            tint = iconType.tint,
        )
    }
}

@Composable
fun FunchIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconType: IconType,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Icon(
        modifier = modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = interactionSource,
        ),
        painter = painterResource(id = iconType.resId),
        contentDescription = iconType.description,
        tint = iconType.tint,
    )
}

/*============================== Preview =================================*/

@Preview(showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
fun FunchLargeIconButtonPreview() {
    FunchIconLargeButton(
        modifier = Modifier.background(
            color = Gray500, shape = RoundedCornerShape(12.dp)
        ),
        onClick = { /*TODO*/ },
        iconType = IconType(
            resId = FunchIconAsset.Search.search_24,
            description = "",
            tint = Yellow500,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun FunchMediumIconButtonPreview() {
    FunchIconButton(
        onClick = { /*TODO*/ },
        iconType = IconType(
            resId = FunchIconAsset.Search.search_24,
            description = "",
            tint = Gray400,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun FunchSmallIconButtonPreview() {
    FunchIconButton(
        onClick = { /*TODO*/ },
        iconType = IconType(
            resId = FunchIconAsset.Search.search_16,
            description = "",
            tint = Gray400,
        ),
    )
}
