package com.moya.funch.match.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moya.funch.entity.match.Recommend
import com.moya.funch.match.theme.Gray300
import com.moya.funch.match.theme.Gray500
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme

@Composable
internal fun RecommendCardContent(recommends: List<Recommend>) {
    Spacer(modifier = Modifier.height(8.dp))
    Text("우리 이런 주제로 대화해봐요", style = FunchTheme.typography.t2, color = White)
    Spacer(modifier = Modifier.height(4.dp))
    Text("지금부터 서로에게 집중하는 시간!", style = FunchTheme.typography.b, color = Gray300)
    RecommendList(recommends)
}

@Composable
private fun RecommendList(recommends: List<Recommend>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        recommends.forEach { recommend ->
            RecommendItem(recommend)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun RecommendItem(recommend: Recommend) {
    Box(
        modifier = Modifier
            .background(Gray500, FunchTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = (13.5).dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = recommend.title, style = FunchTheme.typography.b, color = White)
    }
}
