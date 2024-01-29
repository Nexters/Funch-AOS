package com.moya.funch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moya.funch.R
import com.moya.funch.component.FunchButtonTextField
import com.moya.funch.component.FunchIcon
import com.moya.funch.component.FunchIconButton
import com.moya.funch.icon.FunchIconAsset
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

private val brush =
    Brush.horizontalGradient(
        0.5f to Lemon500,
        0.5f to Color(0xFFFFD440),
    )

@Composable
fun FunchApp(
    matchingCode: String,
    onMatchingCodeChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    myCode: String,
    onMyProfileClick: () -> Unit,
    viewCount: Int,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    top = 8.dp,
                    start = 20.dp,
                    end = 20.dp,
                ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MatchingCard(
            value = matchingCode,
            onValueChange = onMatchingCodeChange,
            onSearchClick = onSearchClick,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CodeCard(
                modifier = Modifier.weight(1f),
                myCode = myCode,
            )
            MyProfileCard(
                onMyProfileClick = onMyProfileClick,
            )
        }
        ProfileViewCounterCard(
            viewCount = viewCount,
        )
    }
}

@Composable
private fun MatchingCard(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    val annotatedString =
        buildAnnotatedString {
            withStyle(
                style =
                    SpanStyle(
                        color = Color.White,
                        fontStyle = FunchTheme.typography.t2.fontStyle,
                        fontFamily = FunchTheme.typography.t2.fontFamily,
                        fontWeight = FunchTheme.typography.t2.fontWeight,
                        fontSize = FunchTheme.typography.t2.fontSize,
                        letterSpacing = FunchTheme.typography.t2.letterSpacing,
                    ),
            ) {
                append(stringResource(id = R.string.matching_card_1))
            }
            withStyle(
                style =
                    SpanStyle(
                        color = Gray300,
                        fontStyle = FunchTheme.typography.b.fontStyle,
                        fontFamily = FunchTheme.typography.b.fontFamily,
                        fontWeight = FunchTheme.typography.b.fontWeight,
                        fontSize = FunchTheme.typography.b.fontSize,
                        letterSpacing = FunchTheme.typography.b.letterSpacing,
                    ),
            ) {
                append(stringResource(id = R.string.matching_card_2))
            }
        }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(178.dp)
                .border(
                    width = 1.dp,
                    brush = brush,
                    shape = RoundedCornerShape(20.dp),
                )
                .clip(RoundedCornerShape(20.dp))
                .background(Gray800)
                .padding(
                    top = 24.dp,
                    bottom = 33.dp,
                    start = 16.dp,
                    end = 16.dp,
                ),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        Text(text = annotatedString)
        FunchButtonTextField(
            backgroundColor = Gray700,
            value = value,
            onValueChange = onValueChange,
            hint = stringResource(id = R.string.matching_card_hint),
            iconButton = {
                FunchIconButton(
                    modifier = Modifier.size(40.dp),
                    roundedCornerShape = RoundedCornerShape(12.dp),
                    backgroundColor = Gray500,
                    onClick = onSearchClick,
                    funchIcon =
                        FunchIcon(
                            resId = FunchIconAsset.Search.search_24,
                            description = "",
                            tint = Yellow500,
                        ),
                )
            },
        )
    }
}

@Composable
private fun CodeCard(
    modifier: Modifier = Modifier,
    myCode: String,
) {
    val annotatedString =
        buildAnnotatedString {
            withStyle(
                style =
                    SpanStyle(
                        color = Gray400,
                        fontStyle = FunchTheme.typography.b.fontStyle,
                        fontFamily = FunchTheme.typography.b.fontFamily,
                        fontWeight = FunchTheme.typography.b.fontWeight,
                        fontSize = FunchTheme.typography.b.fontSize,
                        letterSpacing = FunchTheme.typography.b.letterSpacing,
                    ),
            ) {
                append(stringResource(id = R.string.my_code_card_1))
            }
            withStyle(
                style =
                    SpanStyle(
                        brush = brush,
                        fontStyle = FunchTheme.typography.sbt2.fontStyle,
                        fontFamily = FunchTheme.typography.sbt2.fontFamily,
                        fontWeight = FunchTheme.typography.sbt2.fontWeight,
                        fontSize = FunchTheme.typography.sbt2.fontSize,
                        letterSpacing = FunchTheme.typography.sbt2.letterSpacing,
                    ),
            ) {
                append(stringResource(id = R.string.my_code_card_2, myCode))
            }
        }

    Row(
        modifier =
            modifier
                .background(
                    color = Gray800,
                    shape = FunchTheme.shapes.medium,
                )
                .padding(
                    top = 24.dp,
                    bottom = 24.dp,
                    start = 20.dp,
                ),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = FunchIconAsset.Search.search_24),
            contentDescription = "",
            tint = Gray400,
        )
        Text(text = annotatedString)
    }
}

@Composable
private fun MyProfileCard(
    modifier: Modifier = Modifier,
    onMyProfileClick: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .background(
                    color = Gray800,
                    shape = FunchTheme.shapes.medium,
                )
                .clickable(onClick = onMyProfileClick)
                .padding(
                    vertical = 12.5f.dp,
                    horizontal = 24.dp,
                ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = FunchIconAsset.Search.search_24),
            contentDescription = "",
            tint = Gray400,
        )
        Text(
            text = stringResource(id = R.string.my_profile_card_1),
            style = FunchTheme.typography.b,
            color = Gray400,
        )
    }
}

@Composable
private fun ProfileViewCounterCard(viewCount: Int) {
    val annotatedString =
        buildAnnotatedString {
            withStyle(
                style =
                    SpanStyle(
                        color = Gray400,
                        fontStyle = FunchTheme.typography.b.fontStyle,
                        fontFamily = FunchTheme.typography.b.fontFamily,
                        fontWeight = FunchTheme.typography.b.fontWeight,
                        fontSize = FunchTheme.typography.b.fontSize,
                        letterSpacing = FunchTheme.typography.b.letterSpacing,
                    ),
            ) {
                append(stringResource(id = R.string.profile_view_counter_card_1))
            }
            withStyle(
                style =
                    SpanStyle(
                        color = White,
                        fontStyle = FunchTheme.typography.sbt2.fontStyle,
                        fontFamily = FunchTheme.typography.sbt2.fontFamily,
                        fontWeight = FunchTheme.typography.sbt2.fontWeight,
                        fontSize = FunchTheme.typography.sbt2.fontSize,
                        letterSpacing = FunchTheme.typography.sbt2.letterSpacing,
                    ),
            ) {
                append(stringResource(id = R.string.profile_view_counter_card_2, viewCount))
            }
        }
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = Gray800,
                    shape = FunchTheme.shapes.medium,
                )
                .padding(
                    top = 24.dp,
                    bottom = 24.dp,
                    start = 20.dp,
                ),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = FunchIconAsset.Search.search_24),
            contentDescription = "",
            tint = Gray400,
        )
        Text(text = annotatedString)
    }
}

@Preview(
    "Home UI",
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
)
@Composable
private fun Preview1() {
    var text by remember { mutableStateOf("") }
    val code = "u23c".uppercase()

    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor,
        ) {
            FunchApp(
                matchingCode = text,
                onMatchingCodeChange = { text = it },
                onSearchClick = {},
                myCode = code,
                onMyProfileClick = {},
                viewCount = 23,
            )
        }
    }
}
