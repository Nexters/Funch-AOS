package com.moya.funch.match.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.match.theme.Gray400
import com.moya.funch.match.theme.Gray500
import com.moya.funch.match.theme.Gray800
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme

@Composable
fun MatchPageIndicator(current: Int, pageCount: Int) {
    require(current in 0 until pageCount)
    val annotatedString = buildAnnotatedString {
        append("${current + 1}/$pageCount")
        addStyle(style = SpanStyle(color = White), start = 0, end = 1)
    }

    Box(
        modifier = Modifier
            .background(Gray500, FunchTheme.shapes.large)
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        Text(annotatedString, color = Gray400, style = FunchTheme.typography.caption)
    }
}

@Composable
@Preview(showBackground = true, name = "PagerIndicator")
private fun Preview() {
    FunchTheme {
        Column(
            modifier = Modifier
                .background(Gray800),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MatchPageIndicator(current = 0, pageCount = 3)
            MatchPageIndicator(current = 1, pageCount = 3)
            MatchPageIndicator(current = 2, pageCount = 3)
        }
    }
}
