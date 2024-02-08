package com.moya.funch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.component.FunchIcon
import com.moya.funch.component.FunchIconButton
import com.moya.funch.component.FunchNonTitleTopBar
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400

@Composable
fun FunchTopBar(
    modifier: Modifier = Modifier,
    enabledLeadingIcon: Boolean = true,
    enabledTrailingIcon: Boolean = true,
    onClickLeadingIcon: () -> Unit = {},
    onClickTrailingIcon: () -> Unit = {},
    leadingIcon: FunchIcon? =
        FunchIcon(
            resId = FunchIconAsset.Search.search_24,
            description = "Search",
            tint = Gray400
        ),
    trailingIcon: (@Composable () -> Unit)? = { FunchFeedbackButton(enabledTrailingIcon, onClickTrailingIcon) }
) {
    FunchNonTitleTopBar(
        modifier,
        leadingIcon = {
            if (leadingIcon != null) {
                FunchIconButton(
                    enabled = enabledLeadingIcon,
                    modifier = Modifier.size(40.dp),
                    onClick = onClickLeadingIcon,
                    funchIcon = leadingIcon
                )
            }
        },
        trailingIcon = {
            if (trailingIcon != null) {
                trailingIcon()
            }
        })
}

@Composable
@Preview(showBackground = true, name = "FunchTopBar - Leading and Trailing visible")
private fun Preview() {
    FunchTheme {
        Column(
            modifier = Modifier.background(Gray400),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FunchTopBar()
            FunchTopBar(leadingIcon = null)
            FunchTopBar(trailingIcon = null)
        }
    }
}

@Preview(showBackground = true, name = "FunchTopBar - enabled, disabled")
@Composable
private fun Preview2() {
    FunchTheme {
        Column(
            modifier = Modifier.background(Gray400),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FunchTopBar(Modifier.padding(start = 12.dp, end = 20.dp), enabledLeadingIcon = false)
            FunchTopBar(enabledTrailingIcon = false)
            FunchTopBar(enabledLeadingIcon = false, enabledTrailingIcon = false)
        }
    }
}
