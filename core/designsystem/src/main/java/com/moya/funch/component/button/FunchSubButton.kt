package com.moya.funch.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moya.funch.modifier.clickableSingle
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900

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


@Preview(name = "button Type", showBackground = true, widthDp = 360)
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
                contentHorizontalPadding = 131.dp,
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

@Preview(name = "enabled, disabled 버튼", showBackground = true, widthDp = 360)
@Composable
private fun Preview2() {
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

