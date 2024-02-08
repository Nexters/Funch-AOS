package com.moya.funch.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.match.component.MatchHorizontalPager
import com.moya.funch.match.component.MatchTopBar
import com.moya.funch.match.theme.Gray900
import com.moya.funch.match.theme.White

@Composable
internal fun MatchRoute(onClose: () -> Unit, code: String, matchViewModel: MatchViewModel = hiltViewModel()) {
    val uiState by matchViewModel.uiState.collectAsStateWithLifecycle()
    val matchCode by matchViewModel.matchCode.collectAsStateWithLifecycle()

    LaunchedEffect(matchViewModel) {
        matchViewModel.saveMatchCode(code)
    }

    MatchScreen(
        onClose = onClose,
        memberCode = matchCode,
        matchUiState = uiState
    )
}

@Composable
private fun MatchScreen(onClose: () -> Unit, memberCode: String, matchUiState: MatchUiState) {
    Column(
        modifier = Modifier
            .background(Gray900)
            .fillMaxSize()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatchTopBar(onClose = onClose)
        Spacer(modifier = Modifier.height(8.dp))
        when (matchUiState) {
            is MatchUiState.Loading -> LoadingContent()
            is MatchUiState.Error -> ErrorMatchContent(code = memberCode)
            is MatchUiState.Success -> MatchHorizontalPager(matching = matchUiState.matching)
        }
    }
}

@Composable
internal fun ErrorMatchContent(code: String) {
    Text("There is no match code $code. Please try again.", color = Color.Red)
}

@Composable
internal fun LoadingContent() {
    Text(text = "Loading...", color = White)
}

@Preview(showBackground = true, name = "match screen")
@Composable
private fun MatchScreenPreview() {
    MatchScreen(
        onClose = {},
        memberCode = "1234",
        matchUiState = MatchUiState.Loading
    )
}
