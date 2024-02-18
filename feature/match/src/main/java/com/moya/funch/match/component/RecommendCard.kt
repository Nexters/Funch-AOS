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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.match.R
import com.moya.funch.match.theme.Gray300
import com.moya.funch.match.theme.Gray500
import com.moya.funch.match.theme.Gray800
import com.moya.funch.match.theme.White
import com.moya.funch.theme.FunchTheme

@Composable
internal fun RecommendCard(current: Int, pageCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray800, shape = FunchTheme.shapes.large)
            .padding(top = 20.dp)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatchPageIndicator(current = current, pageCount = pageCount)
        Spacer(modifier = Modifier.height(8.dp))
        RecommendCardContent()
    }
}

@Composable
private fun RecommendCardContent() {
    Spacer(modifier = Modifier.height(8.dp))
    Text("우리 이런 주제로 대화해봐요", style = FunchTheme.typography.t2, color = White)
    Spacer(modifier = Modifier.height(4.dp))
    Text("지금부터 서로에게 집중하는 시간!", style = FunchTheme.typography.b, color = Gray300)
    RecommendList()
}

@Composable
private fun RecommendList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stringArrayResource(id = R.array.match_recommend_labels).forEach {
            RecommendItem(it)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun RecommendItem(recommend: String) {
    Box(
        modifier = Modifier
            .background(Gray500, FunchTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = (13.5).dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = recommend, style = FunchTheme.typography.b, color = White)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FunchTheme {
        RecommendCard(1, 3)
    }
}
