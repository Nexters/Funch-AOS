package com.moya.funch.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.component.FunchButtonType
import com.moya.funch.component.FunchMainButton
import com.moya.funch.onboarding.theme.Gray300
import com.moya.funch.onboarding.theme.Gray900
import com.moya.funch.onboarding.theme.White
import com.moya.funch.theme.FunchTheme

@Composable
internal fun OnBoardingScreen(onNavigateToCreateProfile: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Gray900)
            .fillMaxSize()
            .padding(horizontal = 37.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_sub_title),
            color = Gray300,
            style = FunchTheme.typography.b
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(id = R.string.onboarding_title),
            color = White,
            style = FunchTheme.typography.t1
        )
        Spacer(modifier = Modifier.height(28.dp))
        OnBoardingImage()
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = stringResource(id = R.string.profile_create_recommend),
            color = Gray300,
            style = FunchTheme.typography.b
        )
        Spacer(modifier = Modifier.height(8.dp))
        FunchMainButton(
            buttonType = FunchButtonType.Medium,
            onClick = onNavigateToCreateProfile,
            text = stringResource(id = R.string.profile_create_btn_text),
            contentHorizontalPadding = 24.dp
        )
    }
}

@Composable
private fun OnBoardingImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.onboarding),
        contentDescription = "onBoard"
    )
}

@Composable
@Preview
private fun Preview() {
    FunchTheme {
        OnBoardingScreen({})
    }
}
