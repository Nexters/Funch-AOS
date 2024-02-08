package com.moya.funch.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moya.funch.GRAY_400
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.ui.FunchTopBar

@Composable
internal fun MatchTopBar(onClose: () -> Unit) {
    // @murjune TODO Change leadingIcon tint funchTheme.xxx
    FunchTopBar(
        modifier = Modifier.padding(start = 12.dp, end = 20.dp),
        enabledTrailingIcon = false,
        leadingIcon = FunchIcon(
            resId = FunchIconAsset.Etc.close_24,
            description = "close",
            tint = GRAY_400
        ),
        onClickLeadingIcon = onClose
    )
}
