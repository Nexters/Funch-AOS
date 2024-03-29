package com.moya.funch.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.Coral500
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.White
import com.moya.funch.theme.Yellow500
import com.moya.funch.ui.FunchErrorCaption

@Composable
fun FunchDefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle =
        TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .then(
                        if (isError) {
                            Modifier.border(
                                width = 1.dp,
                                color = Coral500,
                                shape = RoundedCornerShape(16.dp)
                            )
                        } else if (isFocus) {
                            Modifier.border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                        } else {
                            Modifier
                        }
                    )
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = Gray400,
                        fontSize = 14.sp,
                        style = FunchTheme.typography.b
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun FunchMaxLengthTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    hint: String,
    errorText: String,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle =
        TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.height((56 + 24).dp)) {
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Gray800, RoundedCornerShape(16.dp))
                        .then(
                            if (isError) {
                                Modifier.border(
                                    width = 1.dp,
                                    color = Coral500,
                                    shape = RoundedCornerShape(16.dp)
                                )
                            } else if (isFocus) {
                                Modifier.border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                            } else {
                                Modifier
                            }
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box {
                        if (value.isEmpty()) {
                            Text(
                                text = hint,
                                color = Gray400,
                                fontSize = 14.sp,
                                style = FunchTheme.typography.b
                            )
                        }
                        innerTextField()
                    }

                    Text(
                        text =
                        annotatedStringMaxLengthType(
                            isError = isError,
                            value = value,
                            maxLength = maxLength
                        ),
                        style =
                        TextStyle(
                            fontSize = 14.sp,
                            lineHeight = FunchTheme.typography.b.lineHeight,
                            fontFamily = FunchTheme.typography.b.fontFamily,
                            fontWeight = FunchTheme.typography.b.fontWeight
                        )
                    )
                }
                if (isError) {
                    FunchErrorCaption(
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 4.dp
                            ),
                        errorText = errorText
                    )
                }
            }
        }
    )
}

@Composable
fun FunchIconTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    iconType: FunchIcon,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle =
        TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .then(
                        if (isError) {
                            Modifier.border(
                                width = 1.dp,
                                color = Coral500,
                                shape = RoundedCornerShape(16.dp)
                            )
                        } else if (isFocus) {
                            Modifier.border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                        } else {
                            Modifier
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconType.resId),
                    contentDescription = iconType.description,
                    tint = iconType.tint
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Gray400,
                            fontSize = 14.sp,
                            style = FunchTheme.typography.b
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun FunchButtonTextField(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    iconButton: @Composable () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle =
        TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(backgroundColor, RoundedCornerShape(16.dp))
                    .padding(start = 16.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Gray400,
                            fontSize = 14.sp,
                            style = FunchTheme.typography.b
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.width(8.dp))
                iconButton()
            }
        }
    )
}

private fun annotatedStringMaxLengthType(isError: Boolean, value: String, maxLength: Int) = buildAnnotatedString {
    if (!isError) {
        if (value.isEmpty()) {
            withStyle(style = SpanStyle(color = Gray400)) {
                append("${value.length}/$maxLength")
            }
        } else {
            withStyle(style = SpanStyle(color = White)) {
                append("${value.length}")
            }
            withStyle(style = SpanStyle(color = Gray400)) {
                append("/$maxLength")
            }
        }
    } else {
        withStyle(style = SpanStyle(color = Coral500)) {
            append("${value.length}/$maxLength")
        }
    }
}

// ============================== Preview =================================

@Preview("Default", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview1() {
    var text by remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val maxLength = 9

    LaunchedEffect(text) {
        if (text.length < maxLength) {
            isError.value = false
        }
    }

    FunchTheme {
        Column {
            FunchDefaultTextField(
                value = text,
                onValueChange = { innerText -> text = innerText },
                hint = "최대 ${maxLength}글자",
                isError = isError.value
            )
            if (isError.value) {
                FunchErrorCaption(
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp),
                    errorText = "errorText"
                )
            }
            Button(
                onClick = { isError.value = text.length > maxLength }
            ) {
                Text(text = "전송")
            }
        }
    }
}

@Preview("MaxLengthType", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview2() {
    var text by remember { mutableStateOf("ghg홓") }
    val isError = remember { mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val maxLength = 9

    LaunchedEffect(isFocused) {
        if (!isFocused || text.length < maxLength) {
            isError.value = false
        }
    }

    FunchTheme {
        Column {
            FunchMaxLengthTextField(
                value = text,
                onValueChange = { innerText ->
                    if (innerText.length <= maxLength) {
                        text = innerText
                        isError.value = false
                    } else {
                        isError.value = true
                    }
                },
                maxLength = maxLength,
                hint = "최대 ${maxLength}글자",
                isError = isError.value,
                interactionSource = interactionSource,
                isFocus = isFocused,
                errorText = "최대 ${maxLength}글자를 초과했어요"
            )
        }
    }
}

@Preview("Icon", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview3() {
    var text by remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(true) }

    FunchTheme {
        Column {
            FunchIconTextField(
                value = text,
                onValueChange = { innerText -> text = innerText },
                hint = "가까운 지하철역 검색",
                iconType =
                FunchIcon(
                    resId = FunchIconAsset.Search.search_24,
                    description = "",
                    tint = Gray500
                ),
                isError = isError.value
            )
            if (isError.value) {
                FunchErrorCaption(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            top = 4.dp
                        ),
                    errorText = "존재하지 않는 지하철역이에요"
                )
            }
        }
    }
}

@Preview("Button", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
private fun Preview4() {
    var text by remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }

    FunchTheme {
        Column {
            FunchButtonTextField(
                value = text,
                onValueChange = { innerText -> text = innerText },
                hint = "친구 코드를 입력하고 매칭하기",
                backgroundColor = Gray800,
                iconButton = {
                    FunchIconButton(
                        modifier = Modifier.size(40.dp),
                        backgroundColor = Gray500,
                        onClick = { /*TODO*/ },
                        roundedCornerShape = RoundedCornerShape(12.dp),
                        funchIcon =
                        FunchIcon(
                            resId = FunchIconAsset.Search.search_24,
                            description = "",
                            tint = Yellow500
                        )
                    )
                }
            )
            if (isError.value) {
                FunchErrorCaption(
                    errorText = "존재하지 않는 지하철역이에요"
                )
            }
        }
    }
}
