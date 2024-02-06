package com.moya.funch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CreateProfileScreen(onNavigateToHome: () -> Unit) {
    Column {
        Text(
            text = "프로필 생성 화면",
            fontSize = 12.sp,
            color = Color.White
        )
        Button(onClick = onNavigateToHome) {
            Text(text = "프로필 생성")
        }
    }
}
