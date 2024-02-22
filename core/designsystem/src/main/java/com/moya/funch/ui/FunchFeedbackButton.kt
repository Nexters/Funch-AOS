package com.moya.funch.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.component.FunchButtonType
import com.moya.funch.component.FunchSubButton
import com.moya.funch.designsystem.R
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray900

@SuppressWarnings("has Android-specific code - This Component will be deprecated soon")
@Composable
fun FunchFeedbackButton(enabled: Boolean = true, onClick: () -> Unit) {
    val url = "https://forms.gle/fGw4Jv8pQTpug77x6"
    val activity = LocalContext.current.findActivity()
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    FunchSubButton(
        modifier = Modifier.wrapContentSize(),
        enabled = enabled,
        buttonType = FunchButtonType.XSmall,
        onClick = { activity.startActivity(intent) },
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
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FunchFeedbackButton(
                onClick = { }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            FunchFeedbackButton(
                enabled = false,
                onClick = { /*TODO*/ }
            )
        }
    }
}

private fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}
