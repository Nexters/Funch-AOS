package com.moya.funch

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.component.FunchButtonTextField
import com.moya.funch.component.FunchIcon
import com.moya.funch.component.FunchIconButton
import com.moya.funch.home.R
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.modifier.clickableSingle
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray300
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray700
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Lemon500
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.theme.White
import com.moya.funch.theme.Yellow500
import com.moya.funch.ui.FunchTopBar
import com.moya.funch.ui.SingleEventArea
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private val brush = Brush.horizontalGradient(
    0.5f to Lemon500,
    0.5f to Color(0xFFFFD440)
)

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToMyProfile: () -> Unit,
    onNavigateToMatching: (String) -> Unit
) {
    val homeModel by viewModel.homeModel.collectAsStateWithLifecycle()
    val matched by viewModel.matched.collectAsStateWithLifecycle(false)
    val context = LocalContext.current
    val matchDone by rememberUpdatedState(viewModel::matchDone)

    LaunchedEffect(viewModel) {
        viewModel.homeErrorMessage.onEach {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }.launchIn(this)
    }

    if (matched) {
        matchDone()
        onNavigateToMatching(homeModel.matchingCode)
    }

    HomeScreen(
        myCode = homeModel.myCode,
        viewCount = homeModel.viewCount,
        matchingCode = homeModel.matchingCode,
        onMatchingCodeChange = viewModel::setMatchingCode,
        matchProfile = viewModel::matchProfile,
        onNavigateToMyProfile = onNavigateToMyProfile
    )
}

@Composable
internal fun HomeScreen(
    myCode: String,
    viewCount: Int,
    matchingCode: String,
    onMatchingCodeChange: (String) -> Unit,
    matchProfile: () -> Unit,
    onNavigateToMyProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HomeTopBar(onClickFeedBack = {})
        MatchingCard(
            value = matchingCode,
            onValueChange = onMatchingCodeChange,
            matchProfile = matchProfile
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CodeCard(
                modifier = Modifier.weight(1f),
                myCode = myCode
            )
            MyProfileCard(
                onMyProfileClick = onNavigateToMyProfile
            )
        }
        ProfileViewCounterCard(
            viewCount = viewCount
        )
    }
}

@Composable
private fun HomeTopBar(onClickFeedBack: () -> Unit) {
    FunchTopBar(
        modifier = Modifier.padding(bottom = 8.dp),
        leadingIcon = null,
        onClickTrailingIcon = onClickFeedBack
    )
}

@Composable
private fun MatchingCard(value: String, onValueChange: (String) -> Unit, matchProfile: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = brush,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Gray800)
            .padding(
                top = 24.dp,
                bottom = 33.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.matching_card_title),
            style = FunchTheme.typography.t2,
            color = White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(id = R.string.matching_card_caption),
            style = FunchTheme.typography.b,
            color = Gray300
        )
        Spacer(modifier = Modifier.height(16.dp))
        FunchButtonTextField(
            backgroundColor = Gray700,
            value = value,
            onValueChange = onValueChange,
            hint = stringResource(id = R.string.matching_card_hint),
            iconButton = {
                SingleEventArea { cutter ->
                    FunchIconButton(
                        modifier = Modifier.size(40.dp),
                        roundedCornerShape = RoundedCornerShape(12.dp),
                        backgroundColor = Gray500,
                        onClick = { cutter.handle(matchProfile) },
                        funchIcon = FunchIcon(
                            resId = FunchIconAsset.Search.search_24,
                            description = "",
                            tint = Yellow500
                        )
                    )
                }
            }
        )
    }
}

@Composable
private fun CodeCard(modifier: Modifier = Modifier, myCode: String) {
    Row(
        modifier = modifier
            .background(
                color = Gray800,
                shape = FunchTheme.shapes.medium
            )
            .padding(
                top = 24.dp,
                bottom = 24.dp,
                start = 20.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = FunchIconAsset.Etc.code_80),
            contentDescription = "code"
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 2.dp)
        ) {
            Text(
                text = stringResource(id = R.string.my_code_card_caption),
                style = FunchTheme.typography.b,
                color = Gray400
            )
            Text(
                text = stringResource(id = R.string.my_code, myCode),
                style = TextStyle(
                    brush = brush,
                    fontStyle = FunchTheme.typography.sbt2.fontStyle,
                    fontFamily = FunchTheme.typography.sbt2.fontFamily,
                    fontWeight = FunchTheme.typography.sbt2.fontWeight,
                    fontSize = FunchTheme.typography.sbt2.fontSize,
                    letterSpacing = FunchTheme.typography.sbt2.letterSpacing,
                    lineHeight = FunchTheme.typography.sbt2.lineHeight,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Proportional,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        }
    }
}

@Composable
private fun MyProfileCard(modifier: Modifier = Modifier, onMyProfileClick: () -> Unit) {
    Column(
        modifier = modifier
            .background(
                color = Gray800,
                shape = FunchTheme.shapes.medium
            )
            .clip(FunchTheme.shapes.medium)
            .clickableSingle(onClick = onMyProfileClick)
            .padding(
                vertical = 11.5.dp,
                horizontal = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = FunchIconAsset.Etc.profile_80),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.my_profile_card_caption),
            style = FunchTheme.typography.b,
            color = Gray400
        )
    }
}

@Composable
private fun ProfileViewCounterCard(viewCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Gray800,
                shape = FunchTheme.shapes.medium
            )
            .padding(
                top = 24.dp,
                bottom = 24.dp,
                start = 20.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = FunchIconAsset.Etc.view_count_80),
            contentDescription = ""
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 2.dp)
        ) {
            Text(
                text = stringResource(id = R.string.profile_view_counter_caption),
                style = FunchTheme.typography.b,
                color = Gray400
            )
            Text(
                text = stringResource(id = R.string.profile_view_counter_card_subtitle, viewCount),
                style = FunchTheme.typography.sbt2,
                color = White
            )
        }
    }
}

@Preview(
    "Home UI",
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
private fun Preview1() {
    var text by remember { mutableStateOf("") }
    val code = "u23c".uppercase()

    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            HomeScreen(
                myCode = code,
                viewCount = 23,
                matchingCode = text,
                onMatchingCodeChange = { text = it },
                matchProfile = {},
                onNavigateToMyProfile = {}
            )
        }
    }
}
