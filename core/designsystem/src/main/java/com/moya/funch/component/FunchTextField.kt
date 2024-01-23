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

@Composable
fun FunchTextFieldDefaultType(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight,
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .then(
                        if (isFocus)
                            Modifier.border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else if (isError)
                            Modifier.border(
                                width = 1.dp,
                                color = Coral500,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else Modifier
                    )
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = Gray400,
                        fontSize = 14.sp,
                        style = FunchTheme.typography.b,
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun FunchTextFieldMaxLengthType(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    hint: String,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight,
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .then(
                        if (isFocus)
                            Modifier.border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else if (isError)
                            Modifier.border(
                                width = 1.dp,
                                color = Coral500,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else Modifier
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Gray400,
                            fontSize = 14.sp,
                            style = FunchTheme.typography.b,
                        )
                    }
                    innerTextField()
                }

                Text(
                    text = annotatedStringMaxLengthType(
                        isError = isError,
                        value = value,
                        maxLength = maxLength
                    ),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = FunchTheme.typography.b.lineHeight,
                        fontFamily = FunchTheme.typography.b.fontFamily,
                        fontWeight = FunchTheme.typography.b.fontWeight,
                    ),
                )
            }
        }
    )
}

@Composable
fun FunchTextFieldIconType(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    iconType: IconType,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight,
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .then(
                        if (isFocus)
                            Modifier.border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else if (isError)
                            Modifier.border(
                                width = 1.dp,
                                color = Coral500,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else Modifier
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = iconType.resId),
                    contentDescription = iconType.description,
                    tint = iconType.tint,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Gray400,
                            fontSize = 14.sp,
                            style = FunchTheme.typography.b,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun FunchTextFieldButtonType(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    buttonBackGround: Color,
    iconType: IconType,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocus: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = White,
            fontSize = FunchTheme.typography.b.fontSize,
            lineHeight = FunchTheme.typography.b.lineHeight,
            fontFamily = FunchTheme.typography.b.fontFamily,
            fontWeight = FunchTheme.typography.b.fontWeight,
        ),
        cursorBrush = SolidColor(Color(0xFF0074FF)),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Gray800, RoundedCornerShape(16.dp))
                    .then(
                        if (isFocus)
                            Modifier.border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                        else Modifier
                    )
                    .padding(start = 16.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Gray400,
                            fontSize = 14.sp,
                            style = FunchTheme.typography.b,
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.width(8.dp))
                FunchIconLargeButton(
                    modifier = Modifier
                        .background(
                            color = buttonBackGround,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    onClick = onClick,
                    iconType = iconType,
                )
            }
        }
    )
}

private fun annotatedStringMaxLengthType(
    isError: Boolean,
    value: String,
    maxLength: Int,
) = buildAnnotatedString {
    if (!isError) {
        if (value.isEmpty()) {
            withStyle(style = SpanStyle(color = Gray400)) {
                append("${value.length}/${maxLength}")
            }
        } else {
            withStyle(style = SpanStyle(color = White)) {
                append("${value.length}")
            }
            withStyle(style = SpanStyle(color = Gray400)) {
                append("/${maxLength}")
            }
        }
    } else {
        withStyle(style = SpanStyle(color = Coral500)) {
            append("${value.length}/${maxLength}")
        }
    }
}

/*============================== Preview =================================*/

@Preview("Default", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
fun FunchTextFieldDefaultTypePreview() {
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
            FunchTextFieldDefaultType(
                value = text,
                onValueChange = { innerText -> text = innerText },
                hint = "최대 ${maxLength}글자",
                isError = isError.value,
            )
            FunchErrorCaption(
                isError = isError.value,
                errorText = "errorText",
            )
            Button(
                onClick = { isError.value = text.length > maxLength },
            ) {
                Text(text = "전송")
            }
        }
    }
}

@Preview("MaxLengthType", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
fun FunchTextFieldMaxLengthTypePreview() {
    var text by remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
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
            FunchTextFieldMaxLengthType(
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
            )
            FunchErrorCaption(
                isError = isError.value,
                errorText = if (isError.value) "최대 ${maxLength}글자까지 입력할 수 있어요" else "",
            )
        }
    }
}

@Preview("Icon", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
fun FunchTextFieldIconTypePreview() {
    var text by remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }

    FunchTheme {
        Column {
            FunchTextFieldIconType(
                value = text,
                onValueChange = { innerText -> text = innerText },
                hint = "가까운 지하철역 검색",
                iconType = IconType(
                    resId = FunchIconAsset.Search.search_24,
                    description = "",
                    tint = Gray500
                ),
                isError = isError.value,
            )
            FunchErrorCaption(
                isError = isError.value,
                errorText = "존재하지 않는 지하철역이에요",
            )
        }
    }
}

@Preview("Button", showBackground = true, backgroundColor = 0xFF2C2C2C)
@Composable
fun FunchTextFieldButtonTypePreview() {
    var text by remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }

    FunchTheme {
        Column {
            FunchTextFieldButtonType(
                onClick = { /*TODO*/ },
                value = text,
                onValueChange = { innerText -> text = innerText },
                hint = "친구 코드를 입력하고 매칭하기",
                iconType = IconType(
                    resId = FunchIconAsset.Search.search_24,
                    description = "",
                    tint = Yellow500
                ),
                buttonBackGround = Gray500,
            )
            FunchErrorCaption(
                isError = isError.value,
                errorText = "존재하지 않는 지하철역이에요",
            )
        }
    }
}
