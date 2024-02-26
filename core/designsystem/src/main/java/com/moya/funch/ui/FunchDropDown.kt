package com.moya.funch.ui

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.modifier.ScrollBarConfig
import com.moya.funch.modifier.verticalScrollWithScrollbar
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray300
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.theme.White

@Composable
fun FunchDropDownButton(
    modifier: Modifier = Modifier,
    placeHolder: String,
    onClick: () -> Unit,
    isDropDownMenuExpanded: Boolean,
    indication: Indication? = LocalIndication.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = Gray800,
                shape = FunchTheme.shapes.medium
            )
            .then(
                if (isDropDownMenuExpanded) {
                    Modifier.border(
                        width = 1.dp,
                        color = White,
                        shape = FunchTheme.shapes.medium
                    )
                } else {
                    Modifier
                }
            )
            .clickable(
                onClick = onClick,
                indication = indication,
                interactionSource = interactionSource
            )
            .padding(
                top = 8.dp,
                bottom = 8.dp,
                start = 16.dp,
                end = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = placeHolder,
            style = FunchTheme.typography.b,
            color = White
        )
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(
                id = if (isDropDownMenuExpanded) {
                    FunchIconAsset.Arrow.arrow_up_24
                } else {
                    FunchIconAsset.Arrow.arrow_down_24
                }
            ),
            contentDescription = "",
            tint = White
        )
    }
}

@Composable
fun FunchDropDownMenu(
    items: List<String>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onItemSelected: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = Gray800,
                shape = FunchTheme.shapes.medium
            )
            .clip(FunchTheme.shapes.medium)
            .verticalScrollWithScrollbar(
                state = scrollState,
                scrollbarConfig = ScrollBarConfig(
                    indicatorHeight = 39.dp,
                    indicatorThickness = 4.dp,
                    indicatorColor = Gray300,
                    padding = PaddingValues(
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 4.dp
                    )
                )
            )
    ) {
        items.forEachIndexed { index, option ->
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            FunchDropDownItem(
                option = option,
                onItemSelected = { onItemSelected(option) },
                isPressed = isPressed,
                interactionSource = interactionSource
            )
            if (index < items.lastIndex) {
                Divider(
                    color = Gray500,
                    thickness = 0.5f.dp
                )
            }
        }
    }
}

@Composable
fun FunchDropDownItem(
    option: String,
    onItemSelected: (String) -> Unit,
    isPressed: Boolean,
    interactionSource: MutableInteractionSource
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isPressed) Gray500 else Gray800)
            .clickable(
                onClick = { onItemSelected(option) },
                interactionSource = interactionSource,
                indication = null
            )
            .padding(
                start = 16.dp,
                end = 8.dp,
                top = 13.5f.dp,
                bottom = 13.5f.dp
            )
    ) {
        Text(
            text = option,
            color = White,
            style = FunchTheme.typography.b
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
private fun Preview1() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            val bloodTypes = listOf("A형", "B형", "O형", "AB형")
            var placeHolder by remember { mutableStateOf(bloodTypes[0]) }
            var isDropDownMenuExpanded by remember { mutableStateOf(true) }
            var buttonBounds by remember { mutableStateOf(Rect.Zero) }
            val dropDownMenuHeight = 192.dp

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Hello, World!",
                        fontSize = 50.sp,
                        color = White
                    )
                    Text(
                        text = "Hello, World!",
                        fontSize = 50.sp,
                        color = White
                    )
                    Text(
                        text = "Hello, World!",
                        fontSize = 50.sp,
                        color = White
                    )
                    FunchDropDownButton(
                        placeHolder = placeHolder,
                        onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
                        isDropDownMenuExpanded = isDropDownMenuExpanded,
                        indication = null,
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            buttonBounds = coordinates.boundsInRoot()
                            println(buttonBounds.top)
                        }
                    )
                    for (i in 0 until 10) {
                        Text(
                            text = "Hello, World!",
                            fontSize = 50.sp,
                            color = White
                        )
                    }
                }
                if (isDropDownMenuExpanded) {
                    Box(
                        modifier = Modifier
                            .absoluteOffset(
                                x = with(LocalDensity.current) { buttonBounds.left.toDp() },
                                y = with(LocalDensity.current) { buttonBounds.top.toDp() - dropDownMenuHeight - 8.dp }
                            )
                            .width(with(LocalDensity.current) { buttonBounds.width.toDp() })
                            .height(dropDownMenuHeight)
                    ) {
                        FunchDropDownMenu(
                            items = bloodTypes,
                            onItemSelected = { text ->
                                placeHolder = text
                                isDropDownMenuExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
