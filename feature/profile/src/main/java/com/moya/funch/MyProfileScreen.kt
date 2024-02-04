package com.moya.funch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray800
import com.moya.funch.ui.Profile
import com.moya.funch.ui.UsersDistinct

@Composable
internal fun MyProfileRoute(
    viewModel: MyProfileViewModel = hiltViewModel(),
    onCloseMyProfile: () -> Unit,
) {
    val profile = viewModel.profile.collectAsState().value

    MyProfileScreen(
        onCloseMyProfile = onCloseMyProfile,
        profile = profile
    )
}

@Composable
fun MyProfileScreen(
    onCloseMyProfile: () -> Unit,
    profile: Profile,
) {
    Box(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 14.dp,
                start = 20.dp,
                end = 20.dp,
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(FunchTheme.shapes.large)
                .background(
                    color = Gray800,
                    shape = FunchTheme.shapes.large
                )
                .padding(
                    vertical = 24.dp,
                    horizontal = 20.dp
                )
        ) {
            Text(
                text = profile.code,
                style = FunchTheme.typography.b,
                color = Gray400,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = profile.name,
                style = FunchTheme.typography.t2,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            UsersDistinct(profile = profile)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
)
@Composable
private fun Preview1() {
    FunchTheme {
        MyProfileScreen(
            onCloseMyProfile = {},
            profile = Profile.default()
        )
    }
}
