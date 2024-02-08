package com.moya.funch.match.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moya.funch.component.FunchIcon
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.match.theme.Gray400
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
            tint = Gray400
        ),
        trailingIcon = null,
        onClickLeadingIcon = onClose
    )
}
