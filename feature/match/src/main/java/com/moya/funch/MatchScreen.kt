package com.moya.funch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.entity.match.Recommend
import com.moya.funch.entity.profile.Profile

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
    CompositionLocalProvider(LocalContentColor provides Color.White) { // @murjune TODO Delete this line
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (matchUiState) {
                is MatchUiState.Loading -> Loading()
                is MatchUiState.Error -> ErrorMatchContent(code = memberCode)
                is MatchUiState.Success -> {
                    val (profile, similarity, chemistrys, recommends, subways) = matchUiState.matching
                    MatchContent(
                        profile = profile,
                        similarity = similarity,
                        chemistrys = chemistrys,
                        recommends = recommends,
                        subways = subways
                    )
                }
            }
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    }
}

@Composable
internal fun MatchContent(
    profile: Profile,
    similarity: Int,
    chemistrys: List<Chemistry>,
    recommends: List<Recommend>,
    subways: List<SubwayStation>
) {
    Column {
        Text("This is Match Screen")
        Text(text = "Profile : $profile")
        Text(text = "Similarity : $similarity")
        Text(text = "Chemistrys : $chemistrys")
        Text(text = "Recommends : $recommends")
        Text(text = "Subways : $subways")
    }
}

@Composable
internal fun ErrorMatchContent(code: String) {
    Text("There is no match code $code. Please try again.")
}

@Composable
internal fun Loading() {
    Text(text = "Loading...")
}
