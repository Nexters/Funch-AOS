package com.moya.funch.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.match.component.MatchTopBar
import com.moya.funch.entity.match.Matching
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
        onClose = onClose, memberCode = matchCode, matchUiState = uiState
    )
}

@Composable
private fun MatchScreen(onClose: () -> Unit, memberCode: String, matchUiState: MatchUiState) {
    Column(
        modifier = Modifier
            .background(Gray900)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatchTopBar(onClose = onClose)
        Spacer(modifier = Modifier.height(8.dp))
        when (matchUiState) {
            is MatchUiState.Loading -> LoadingContent()
            is MatchUiState.Error -> ErrorMatchContent(code = memberCode)
            is MatchUiState.Success -> MatchContent(matching = matchUiState.matching)
        }
    }
}

@Composable
internal fun MatchContent(matching: Matching) {
    val (profile, similarity, chemistrys, recommends, subways) = matching
    CompositionLocalProvider(LocalContentColor provides Color.White) {
        Column {
            Text("This is Match Screen")
            Text(text = "Profile : $profile")
            Text(text = "Similarity : $similarity")
            Text(text = "Chemistrys : $chemistrys")
            Text(text = "Recommends : $recommends")
            Text(text = "Subways : $subways")
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
        onClose = {}, memberCode = "1234", matchUiState = MatchUiState.Loading
    )
}
