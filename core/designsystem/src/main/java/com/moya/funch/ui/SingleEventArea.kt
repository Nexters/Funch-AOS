package com.moya.funch.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moya.funch.modifier.DefaultSingleEventHandler
import com.moya.funch.modifier.SingleEventHandler

/**
 * 여러 번 클릭 이벤트를 막아주는 Wrapper Composable
 * */
@Composable
fun <T> SingleEventArea(content: @Composable (SingleEventHandler) -> T) {
    val singleEventHandler = remember { DefaultSingleEventHandler() }

    content(singleEventHandler)
}
