package com.moya.funch.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.Coral500
import com.moya.funch.theme.FunchTheme

@Composable
fun FunchErrorCaption(modifier: Modifier = Modifier, errorText: String, errorIcon: @Composable (() -> Unit)? = null) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (errorIcon != null) {
            errorIcon()
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = errorText,
            color = Coral500,
            style = FunchTheme.typography.caption
        )
    }
}

// ============================== Preview =================================

@Preview("Error Caption With Icon", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview1() {
    FunchTheme {
        FunchErrorCaption(
            errorIcon = {
                Icon(
                    painter = painterResource(id = FunchIconAsset.Etc.information_24),
                    tint = Coral500,
                    contentDescription = null
                )
            },
            errorText = "errorText"
        )
    }
}

@Preview("Only Text Error Caption", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview2() {
    FunchTheme {
        FunchErrorCaption(
            errorText = "errorText"
        )
    }
}
