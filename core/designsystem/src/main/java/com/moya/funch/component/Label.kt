package com.moya.funch.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400

@Composable
fun FunchSmallLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = Modifier
            .width(52.dp)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = Gray400,
            style = FunchTheme.typography.b
        )
    }
}

@Composable
fun FunchLargeLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .width(52.dp)
            .height(56.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = Gray400,
            style = FunchTheme.typography.b
        )
    }
}
