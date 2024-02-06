package com.moya.funch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray900
import com.moya.funch.ui.FunchFeedbackButton

@Composable
fun FunchNonTitleTopBar(leadingIcon: (@Composable () -> Unit)? = {}, trailingIcon: (@Composable () -> Unit) = {}) {
    val arrangement =
        if (leadingIcon != null) {
            Arrangement.SpaceBetween
        } else {
            Arrangement.End
        }
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .background(Gray900),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) leadingIcon()
        Spacer(modifier = Modifier)
        trailingIcon()
    }
}

@Composable
@Preview(showBackground = true, name = "FunchNonTitleTopBar - Leading and Trailing Icon")
private fun Preview() {
    FunchTheme {
        FunchNonTitleTopBar(
            leadingIcon = {
                FunchIconButton(
                    modifier = Modifier.size(40.dp),
                    onClick = { },
                    funchIcon =
                    FunchIcon(
                        resId = FunchIconAsset.Search.search_24,
                        description = "Search",
                        tint = Gray400
                    )
                )
            },
            trailingIcon = {
                FunchFeedbackButton(onClick = {})
            }
        )
    }
}

@Composable
@Preview(showBackground = true, name = "FunchNonTitleTopBar - No Trailing Icon")
private fun Preview2() {
    FunchTheme {
        FunchNonTitleTopBar(
            leadingIcon = {
                FunchIconButton(
                    modifier = Modifier.size(40.dp),
                    onClick = { },
                    funchIcon =
                    FunchIcon(
                        resId = FunchIconAsset.Search.search_24,
                        description = "Search",
                        tint = Gray400
                    )
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true, name = "FunchNonTitleTopBar - Only Trailing Icon")
private fun Preview3() {
    FunchTheme {
        FunchNonTitleTopBar(
            trailingIcon = {
                FunchFeedbackButton(onClick = {})
            }
        )
    }
}
