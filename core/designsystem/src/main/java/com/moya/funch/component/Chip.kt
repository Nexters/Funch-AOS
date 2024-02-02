package com.moya.funch.component

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.modifier.neonSign
import com.moya.funch.theme.FunchRadiusDefaults
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.Lemon500
import com.moya.funch.theme.White
import com.moya.funch.theme.Yellow500

private val FunchChipContentMinHeight = 21.dp
private val FunchMinHeight = 48.dp
val MATCHED_BORDER_BRUSH = Brush.horizontalGradient(
    listOf(Lemon500, Yellow500)
)

@Composable
fun FunchChip(
    modifier: Modifier = Modifier,
    matched: Boolean = false,
    selected: Boolean,
    enabled: Boolean,
    onSelected: () -> Unit = {},
    shape: CornerBasedShape = FunchTheme.shapes.medium,
    label: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: PunchChipColors = PunchChipColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val indication = if (enabled) LocalIndication.current else null
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = FunchMinHeight)
            .then(
                if (matched) {
                    Modifier.neonSign(
                        borderRadius = FunchRadiusDefaults.Medium,
                        blurRadius = 5.dp,
                        spread = PaddingValues((-1).dp)
                    )
                } else Modifier
            )
            .background(
                color = colors.provideContainerColor(enabled, selected),
                shape = shape
            )
            .then(
                if (matched) {
                    Modifier.border(1.dp, MATCHED_BORDER_BRUSH, shape)
                } else Modifier
            )
            .selectable(
                selected = selected,
                enabled = enabled,
                onClick = onSelected,
                interactionSource = interactionSource,
                indication = indication,
            )
            .padding(provideChipPadding(leadingIcon != null)), verticalAlignment = Alignment.CenterVertically
    ) {
        ChipContent(
            label = label,
            labelColor = colors.provideLabelColor(enabled, selected),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun ChipContent(
    label: @Composable () -> Unit,
    labelTextStyle: TextStyle = FunchTheme.typography.b,
    labelColor: Color,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    minHeight: Dp = FunchChipContentMinHeight,
    paddingValues: PaddingValues,
) {
    CompositionLocalProvider(
        LocalContentColor provides labelColor, LocalTextStyle provides labelTextStyle
    ) {
        Row(
            Modifier
                .defaultMinSize(minHeight = minHeight)
                .padding(paddingValues),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(Modifier.width(8.dp))
            }
            label()
            if (trailingIcon != null) {
                Spacer(Modifier.width(4.dp))
                trailingIcon()
            }
        }
    }
}

private fun provideChipPadding(
    hasLeadingIcon: Boolean = false,
): PaddingValues {
    val start = if (hasLeadingIcon) 8.dp else 16.dp
    val end = 16.dp
    return PaddingValues(start = start, end = end)
}

@Immutable
data class PunchChipColors(
    private val containerColor: Color = Gray800,
    private val selectedContainerColor: Color = Gray500,
    private val disabledContainerColor: Color = Gray800,
    private val disabledSelectedContainerColor: Color = Gray500,
    private val labelColor: Color = Gray400,
    private val selectedLabelColor: Color = White,
    private val disabledLabelColor: Color = Gray400,
    private val disabledSelectedLabelColor: Color = White,
    // private val
) {
    @Stable
    fun provideContainerColor(enabled: Boolean, selected: Boolean): Color {
        return when {
            enabled && selected -> selectedContainerColor
            !enabled && selected -> disabledSelectedContainerColor
            !enabled -> disabledContainerColor
            else -> containerColor
        }
    }

    @Stable
    fun provideLabelColor(enabled: Boolean, selected: Boolean): Color {
        return when {
            enabled && selected -> selectedLabelColor
            !enabled && selected -> disabledSelectedLabelColor
            !enabled -> disabledLabelColor
            else -> labelColor
        }
    }
}

/**
 * Preview
 * */
@Preview(
    name = "FunchChip - Chip with leading and trailing icon", showBackground = true
)
@Composable
private fun Preview1() {
    FunchTheme {
        val isSelected = remember { mutableStateOf(false) }
        val onSelected = { isSelected.value = !isSelected.value }

        FunchChip(
            selected = isSelected.value,
            enabled = true,
            onSelected = onSelected,
            leadingIcon = { LeadingIconForPreview() },
            trailingIcon = { TrailingIconForPreview() },
            label = { Text(text = "안뇽") },
        )
    }
}

@Composable
@Preview(
    name = "FunchChip - Chip with leading icon",
    showBackground = true,
)
private fun Preview2() {
    FunchTheme {
        val isSelected = remember { mutableStateOf(false) }
        val onSelected = { isSelected.value = !isSelected.value }
        FunchChip(
            selected = isSelected.value,
            enabled = true,
            onSelected = onSelected,
            leadingIcon = { LeadingIconForPreview() },
            label = { Text(text = "안뇽") },
        )
    }
}

@Composable
@Preview(
    name = "FunchChip - Chip with trailing icon", showBackground = true
)
private fun Preview3() {
    FunchTheme {
        val isSelected = remember { mutableStateOf(false) }
        val onSelected = { isSelected.value = !isSelected.value }
        FunchChip(
            selected = isSelected.value,
            enabled = true,
            onSelected = onSelected,
            trailingIcon = { TrailingIconForPreview() },
            label = { Text(text = "안뇽") },
        )
    }
}

@Preview(
    name = "FunchChip - non interactive", showBackground = true
)
@Composable
private fun Preview4() {
    FunchTheme {
        Column(Modifier.background(Gray900)) {
            Spacer(modifier = Modifier.size(12.dp))

            FunchChip(
                selected = false,
                enabled = false,
                leadingIcon = { LeadingIconForPreview() },
                label = { Text(text = "안뇽") },
            )
            Spacer(modifier = Modifier.size(12.dp))

            FunchChip(
                selected = true,
                enabled = false,
                leadingIcon = { LeadingIconForPreview() },
                label = { Text(text = "안뇽") },
            )
        }
    }
}

@Preview(
    name = "FunchChip - matched", showBackground = true
)
@Composable
private fun Preview5() {
    FunchTheme {
        Column(
            Modifier
                .background(Gray900)
                .padding(10.dp)
        ) {
            FunchChip(
                matched = true,
                selected = false,
                enabled = false,
                leadingIcon = { LeadingIconForPreview() },
                label = { Text(text = "안뇽") },
            )
        }
    }
}

@Composable
private fun TrailingIconForPreview() {
    Row {
        Box(
            modifier = Modifier
                .background(White, CircleShape)
                .border(1.dp, Color.Green, CircleShape)
                .size(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "2", style = FunchTheme.typography.caption, color = Color.Green)
        }
        Spacer(modifier = Modifier.size(2.dp))
        Box(
            modifier = Modifier
                .background(White, CircleShape)
                .border(1.dp, Color.Blue, CircleShape)
                .size(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "3", style = FunchTheme.typography.caption, color = Color.Blue)
        }
        Spacer(modifier = Modifier.size(2.dp))
        Box(
            modifier = Modifier
                .background(White, CircleShape)
                .border(1.dp, Color.Red, CircleShape)
                .size(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "4", style = FunchTheme.typography.caption, color = Color.Red)
        }
    }
}

@Composable
private fun LeadingIconForPreview() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Gray900),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = FunchIconAsset.Arrow.arrow_up_limit_24),
            contentDescription = "d",
            tint = Color.Red
        )
    }
}
