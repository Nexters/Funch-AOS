package com.moya.funch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.LocalBackgroundTheme

@Composable
fun FunchDialog(
    title: String,
    text: String,
    dismissText: String,
    confirmText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        titleContentColor = Color(0xFFE6E0E9),
        text = {
            Text(text = text)
        },
        textContentColor = Color(0xFFCAC4D0),
        containerColor = Color(0xFF2B2930),
        confirmButton = {
            Box(
                modifier = Modifier
                    .clickable(onClick = onConfirm)
                    .padding(vertical = 10.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = confirmText,
                    color = Color(0xFFD0BCFF)
                )
            }
        },
        onDismissRequest = { onDismiss() },
        dismissButton = {
            Box(
                modifier = Modifier
                    .clickable(onClick = onDismiss)
                    .padding(vertical = 10.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = dismissText,
                    color = Color(0xFFD0BCFF)
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview1() {
    FunchTheme {
        Surface(color = LocalBackgroundTheme.current.color) {
            FunchDialog(
                title = "프로필 삭제하기",
                text = "기존 프로필이 삭제되고 복구가 불가능해요.\n정말 삭제하실 건가요?",
                dismissText = "취소하기",
                confirmText = "삭제하기",
                onDismiss = {},
                onConfirm = {}
            )
        }
    }
}
