package com.moya.funch.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.Coral500
import com.moya.funch.theme.FunchTheme

@Composable
fun FunchErrorCaption(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String,
    description: String = ""
) {
    if (isError) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = FunchIconAsset.Etc.information_24),
                contentDescription = description,
                tint = Coral500
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = errorText,
                color = Coral500,
                style = FunchTheme.typography.caption
            )
        }
    }
}

// ============================== Preview =================================

@Preview("Error Caption", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview1() {
    val isError = remember { mutableStateOf(true) }

    FunchTheme {
        FunchErrorCaption(
            modifier =
            Modifier
                .padding(top = 4.dp, start = 4.dp),
            isError = isError.value,
            errorText = "errorText"
        )
    }
}
