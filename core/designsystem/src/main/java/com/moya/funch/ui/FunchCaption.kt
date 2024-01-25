package com.moya.funch.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
    isError: Boolean = false,
    errorText: String,
    description: String = "",
) {
    if (isError) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(start = 8.dp, end = 4.dp),
                painter = painterResource(id = FunchIconAsset.Etc.information_24),
                contentDescription = description,
                tint = Coral500,
            )
            Text(
                text = errorText,
                color = Coral500,
                style = FunchTheme.typography.caption,
            )
        }
    }
}

/*============================== Preview =================================*/

@Preview("ErrorCaption", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
fun FunchErrorCaptionPreview() {
    val isError = remember { mutableStateOf(true) }

    FunchTheme {
        FunchErrorCaption(
            isError = isError.value,
            errorText = "errorText",
        )
    }
}
