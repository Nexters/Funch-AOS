package com.moya.funch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray700
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.Lemon500

@Composable
private fun FunchNeonSignCard(
    // TODO : @murjune : match feature 이동 예정
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val brush =
        Brush.verticalGradient(
            0.1f to Lemon500,
            0.8f to Gray700
        )
    Column(
        modifier =
        modifier
            .background(Gray800, shape = FunchTheme.shapes.large)
            .border(1.dp, brush, FunchTheme.shapes.large)
            .clip(FunchTheme.shapes.large),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
    }
}

@Composable
@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    name = "FunchNeonSignCard"
)
private fun Preview() {
    FunchTheme {
        Column(
            modifier =
            Modifier
                .background(Gray900)
                .padding(60.dp)
        ) {
            FunchNeonSignCard(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "우리가 누구?", color = Color.White)
                Text(text = "다이나믹 듀오~~", color = Color.White)
            }
        }
    }
}
