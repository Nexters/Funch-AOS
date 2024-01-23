package com.moya.funch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.modifier.clickableSingle
import com.moya.funch.modifier.punchShadow
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.Lemon500
import com.moya.funch.theme.Lemon900
import com.moya.funch.theme.Yellow500

private val DefaultContentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp)

@Composable
fun SharingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = DefaultContentPadding,
    content: @Composable () -> Unit,
) {
    val brush = Brush.horizontalGradient(listOf(Lemon500, Yellow500))
    val disabledColor = Lemon900
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (enabled) Modifier.punchShadow(
                    color = Lemon500, borderRadius = 16.dp
                ) else Modifier
            )
            .clip(RoundedCornerShape(size = 16.dp))
            .then(
                if (enabled) Modifier.background(brush = brush)
                else Modifier.background(color = disabledColor)
            )
            .clickableSingle(enabled = enabled, onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
    Surface {

    }
}

@Preview(showBackground = true)
@Composable
fun SharingButtonPreview() {
    FunchTheme {
        Column(
            Modifier
                .background(Color.Black)
                .width(360.dp)
                .padding(horizontal = 24.dp, vertical = 24.dp),
        ) {
            SharingButton(onClick = { }) {
                Text(
                    text = "Button",
                    style = FunchTheme.typography.sbt1,
                    color = Gray900,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))

            SharingButton(enabled = false, onClick = { }) {
                Text(
                    text = "Button",
                    style = FunchTheme.typography.sbt1,
                    color = Gray900,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))

            SharingButton(modifier = Modifier.width(120.dp), onClick = { }) {
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

