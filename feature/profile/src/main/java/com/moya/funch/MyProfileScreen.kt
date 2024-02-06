package com.moya.funch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MyProfileScreen(onCloseMyProfile: () -> Unit) {
    Column {
        Text(
            text = "내 프로필 화면",
            fontSize = 12.sp,
            color = Color.White
        )
        Button(onClick = onCloseMyProfile) {
            Text(text = "홈으로 가기")
        }
    }
}
