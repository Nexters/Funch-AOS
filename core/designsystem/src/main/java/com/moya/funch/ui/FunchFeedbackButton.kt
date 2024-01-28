package com.moya.funch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.component.FunchButtonType
import com.moya.funch.component.FunchSubButton
import com.moya.funch.designsystem.R
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray900

@Composable
internal fun FunchFeedbackButton(
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    FunchSubButton(
        modifier = Modifier.wrapContentSize(),
        enabled = enabled,
        buttonType = FunchButtonType.XSmall,
        onClick = onClick,
        text = stringResource(id = R.string.send_feed_back),
        contentHorizontalPadding = 12.dp
    )
}

@Preview(name = "feedbackButton - enabled, disabled", showBackground = true, widthDp = 140)
@Composable
private fun Preview1() {
    FunchTheme {
        Column(
            Modifier
                .background(Gray900)
                .padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FunchFeedbackButton(
                onClick = { },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            FunchFeedbackButton(
                enabled = false,
                onClick = { /*TODO*/ },
            )
        }
    }
}
