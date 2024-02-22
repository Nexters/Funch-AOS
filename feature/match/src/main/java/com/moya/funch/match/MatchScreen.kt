package com.moya.funch.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.moya.funch.component.FunchIcon
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.match.MatchViewModel.Companion.MOCK_MATCHING
import com.moya.funch.match.component.MatchHorizontalPager
import com.moya.funch.match.component.MatchTopBar
import com.moya.funch.match.model.MatchProfileUiModel
import com.moya.funch.match.theme.Gray400
import com.moya.funch.match.theme.Gray900
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.ui.FunchTopBar

@Composable
internal fun MatchRoute(onClose: () -> Unit, code: String, matchViewModel: MatchViewModel = hiltViewModel()) {
    val uiState by matchViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(matchViewModel) {
        matchViewModel.saveMatchCode(code)
    }

    when (uiState) {
        is MatchUiState.Loading -> LoadingContent(onClose = onClose)
        is MatchUiState.Error -> ErrorMatchContent(code)
        is MatchUiState.Success -> {
            MatchScreen(
                onClose = onClose,
                matching = (uiState as MatchUiState.Success)
            )
        }
    }
}

@Composable
private fun MatchScreen(onClose: () -> Unit, matching: MatchUiState.Success) {
    val (profile, similarity, chemistrys, subwayChemistry) = matching
    Column(
        modifier = Modifier
            .background(Gray900)
            .fillMaxSize()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatchTopBar(onClose = onClose)
        Spacer(modifier = Modifier.height(8.dp))
        MatchHorizontalPager(profile, similarity, chemistrys, subwayChemistry)
    }
}

@Composable
private fun ErrorMatchContent(code: String) {
    Text("There is no match code $code. Please try again.", color = Color.Red)
}

@Composable
private fun LoadingContent(onClose: () -> Unit) {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.funch_character_loading
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FunchTopBar(
            modifier = Modifier.padding(start = 12.dp),
            trailingIcon = null,
            leadingIcon = FunchIcon(
                resId = FunchIconAsset.Arrow.arrow_left_small_24,
                description = "",
                tint = Gray400
            ),
            onClickLeadingIcon = onClose
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier.size(200.dp),
                composition = lottieComposition,
                iterations = LottieConstants.IterateForever
            )
            Text(
                text = stringResource(id = R.string.match_loading_title),
                color = White,
                style = FunchTheme.typography.t2
            )
        }
    }
}

@Preview(showBackground = true, name = "Success match screen", device = "id:Nexus 6")
@Composable
private fun Preview1() {
    FunchTheme {
        Surface(color = LocalBackgroundTheme.current.color) {
            MatchScreen(
                onClose = {},
                matching = MatchUiState.Success(
                    MatchProfileUiModel.from(MOCK_MATCHING),
                    MOCK_MATCHING.similarity,
                    MOCK_MATCHING.chemistrys,
                    MOCK_MATCHING.subwayChemistry
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Loading match screen", device = "id:Nexus 6")
@Composable
private fun Preview2() {
    FunchTheme {
        Surface(color = LocalBackgroundTheme.current.color) {
            LoadingContent(onClose = {})
        }
    }
}
