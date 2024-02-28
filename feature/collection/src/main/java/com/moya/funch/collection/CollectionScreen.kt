package com.moya.funch.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.collection.theme.Gray300
import com.moya.funch.collection.theme.Gray400
import com.moya.funch.collection.theme.Gray800
import com.moya.funch.collection.theme.White
import com.moya.funch.common.activeMbtiBadgePainter
import com.moya.funch.common.inactiveMbtiBadgePainter
import com.moya.funch.component.FunchIcon
import com.moya.funch.entity.Mbti
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.ui.FunchTopBar

@Composable
internal fun CollectionRoute(viewModel: CollectionViewModel = hiltViewModel(), onNavigateToHome: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CollectionScreen(
        name = uiState.name,
        mbtiCollection = uiState.mbtiCollection,
        onNavigateToHome = onNavigateToHome
    )
}

@Composable
internal fun CollectionScreen(name: String, mbtiCollection: List<Mbti>, onNavigateToHome: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FunchTopBar(
            modifier = Modifier.padding(start = 12.dp),
            onClickLeadingIcon = onNavigateToHome,
            leadingIcon = FunchIcon(
                resId = FunchIconAsset.Arrow.arrow_left_small_24,
                description = "",
                tint = Gray400
            ),
            trailingIcon = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.collection_title),
                color = White,
                style = FunchTheme.typography.t2
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(id = R.string.collection_sub_title, name),
                color = Gray300,
                style = FunchTheme.typography.b
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "MBTI",
                color = Gray400,
                style = FunchTheme.typography.sbt2
            )
            Spacer(modifier = Modifier.height(12.dp))
            MbtiCollection(mbtiCollection = mbtiCollection)
        }
    }
}

@Composable
private fun MbtiCollection(mbtiCollection: List<Mbti>) {
    val mbtiList = Mbti.entries.filter { it != Mbti.IDLE }.toList()

    LazyVerticalGrid(
        modifier = Modifier.widthIn(max = 320.dp),
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(count = mbtiList.size) { item ->
                Box(
                    modifier = Modifier
                        .clip(FunchTheme.shapes.medium)
                        .background(
                            color = Gray800,
                            shape = FunchTheme.shapes.medium
                        )
                        .padding(7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = if (mbtiCollection.contains(mbtiList[item])) {
                            activeMbtiBadgePainter(value = mbtiList[item].name)
                        } else {
                            inactiveMbtiBadgePainter(value = mbtiList[item].name)
                        },
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    )
}

@Preview(
    "Home UI",
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
private fun Preview1() {
    FunchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LocalBackgroundTheme.current.color
        ) {
            CollectionScreen(
                name = "홍길동",
                mbtiCollection = listOf(
                    Mbti.ISTJ,
                    Mbti.INFJ,
                    Mbti.INTJ,
                    Mbti.INFP,
                    Mbti.INTP
                ),
                onNavigateToHome = {}
            )
        }
    }
}
