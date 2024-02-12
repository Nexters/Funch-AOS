package com.moya.funch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.common.clubPainter
import com.moya.funch.common.jobPainter
import com.moya.funch.component.FunchButtonType
import com.moya.funch.component.FunchChip
import com.moya.funch.component.FunchIcon
import com.moya.funch.component.FunchIconTextField
import com.moya.funch.component.FunchLargeLabel
import com.moya.funch.component.FunchMainButton
import com.moya.funch.component.FunchMaxLengthTextField
import com.moya.funch.component.FunchSmallLabel
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.profile.Profile
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray300
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.theme.White
import com.moya.funch.ui.FunchDropDownButton
import com.moya.funch.ui.FunchDropDownMenu
import com.moya.funch.ui.FunchTopBar
import com.moya.funch.uimodel.MbtiItem
import com.moya.funch.uimodel.ProfileLabel

@Composable
internal fun CreateProfileRoute(
    onNavigateToHome: () -> Unit,
    viewModel: CreateProfileViewModel = hiltViewModel()
) {
    val profile by viewModel.profile.collectAsStateWithLifecycle()

    CreateProfileScreen(
        profile = profile,
        isSelectMbti = viewModel::isSelectMbti,
        onSelectJob = viewModel::setJob,
        onSelectClub = viewModel::setClub,
        onSelectMbti = viewModel::setMbti,
        onSelectBloodType = viewModel::setBloodType,
        onNicknameChange = viewModel::setNickname,
        onSubwayStationChange = viewModel::setSubwayName,
        onNavigateToHome = onNavigateToHome,
        onSendFeedback = {}
    )
}

@Composable
fun CreateProfileScreen(
    profile: Profile,
    isSelectMbti: (MbtiItem) -> Boolean,
    onSelectJob: (Job) -> Unit,
    onSelectClub: (Club) -> Unit,
    onSelectMbti: (MbtiItem) -> Unit,
    onSelectBloodType: (Blood) -> Unit,
    onNicknameChange: (String) -> Unit,
    onSubwayStationChange: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onSendFeedback: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val backgroundColor = LocalBackgroundTheme.current.color
    var isKeyboardVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FunchTopBar(
                modifier = Modifier.padding(end = 20.dp),
                leadingIcon = null,
                onClickTrailingIcon = onSendFeedback
            )
        },
        bottomBar = {
            if (!isKeyboardVisible) {
                BottomBar(backgroundColor = backgroundColor, onNavigateToHome = onNavigateToHome)
            }
        },
        containerColor = backgroundColor,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
                .padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "프로필 만들기",
                    color = White,
                    style = FunchTheme.typography.t2
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "프로필을 만들어 공통점을 찾을 수 있어요",
                    color = Gray300,
                    style = FunchTheme.typography.b
                )
                Spacer(modifier = Modifier.height(24.dp))
                NicknameRow(
                    nickname = profile.name,
                    onNicknameChange = onNicknameChange,
                    isKeyboardVisible = { isKeyboardVisible = it }
                )
                Spacer(modifier = Modifier.height(14.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    JobRow(profile = profile, onSelected = onSelectJob)
                    ClubRow(onSelectClub = onSelectClub)
                    MbtiRow(onSelectMbti = onSelectMbti, isSelectMbti = isSelectMbti)
                    BooldTypeRow(onSelectBloodType = onSelectBloodType)
                    SubwayRow(
                        subwayStation = profile.subways[0].name,
                        onSubwayStationChange = onSubwayStationChange,
                        isKeyboardVisible = { isKeyboardVisible = it }
                    )
                }
                Spacer(modifier = Modifier.height(39.dp))
            }
            if (isKeyboardVisible) {
                BottomBar(backgroundColor = backgroundColor, onNavigateToHome = onNavigateToHome)
            }
        }
    }
}

private const val MAX_NICKNAME_LENGTH = 9

@Composable
private fun NicknameRow(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    isKeyboardVisible: (Boolean) -> Unit,
) {
    var isNicknameError by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isFocused) {
        if (!isFocused || nickname.length < MAX_NICKNAME_LENGTH) {
            isNicknameError = false
        }
        isKeyboardVisible(isFocused)
    }

    Row {
        FunchLargeLabel(text = "닉네임")
        Column {
            FunchMaxLengthTextField(
                value = nickname,
                onValueChange = { innerText ->
                    isNicknameError = if (innerText.length <= MAX_NICKNAME_LENGTH) {
                        onNicknameChange(innerText)
                        false
                    } else {
                        true
                    }
                },
                maxLength = MAX_NICKNAME_LENGTH,
                hint = "최대 ${MAX_NICKNAME_LENGTH}글자",
                isError = isNicknameError,
                interactionSource = interactionSource,
                isFocus = isFocused,
                errorText = "최대 ${MAX_NICKNAME_LENGTH}글자까지 입력할 수 있어요",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun JobRow(
    profile: Profile,
    onSelected: (Job) -> Unit
) {
    Row {
        FunchSmallLabel(text = "직군")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Job.entries.filterNot { it == Job.IDLE }.forEach { job ->
                FunchChip(
                    selected = profile.job == job,
                    enabled = true,
                    onSelected = { onSelected(job) },
                    label = {
                        Text(
                            text = job.krName,
                            style = FunchTheme.typography.b,
                            color = White
                        )
                    },
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(
                                    color = Gray900,
                                    shape = FunchTheme.shapes.extraSmall
                                )
                                .clip(FunchTheme.shapes.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(18.dp),
                                painter = jobPainter(job.krName),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ClubRow(
    onSelectClub: (Club) -> Unit
) {
    Row {
        FunchSmallLabel(text = "동아리")
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Club.entries.filterNot { it == Club.IDLE }.forEach { club ->
                var isSelected by remember { mutableStateOf(false) }
                FunchChip(
                    selected = isSelected,
                    enabled = true,
                    onSelected = {
                        onSelectClub(club)
                        isSelected = !isSelected
                    },
                    label = {
                        Text(
                            text = club.label,
                            style = FunchTheme.typography.b,
                            color = White
                        )
                    },
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(
                                    color = Gray900,
                                    shape = FunchTheme.shapes.extraSmall
                                )
                                .clip(FunchTheme.shapes.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(18.dp),
                                painter = clubPainter(club.label),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MbtiRow(
    onSelectMbti: (MbtiItem) -> Unit,
    isSelectMbti: (MbtiItem) -> Boolean,
) {
    val mbtiList = MbtiItem.entries.chunked(2)

    Row {
        FunchSmallLabel(text = ProfileLabel.MBTI.labelName)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            mbtiList.forEachIndexed { i, pair ->
                Column(
                    modifier = Modifier
                        .background(color = Gray800, shape = FunchTheme.shapes.medium)
                        .clip(FunchTheme.shapes.medium)
                ) {
                    pair.forEach { mbti ->
                        MbtiButton(
                            mbtiItem = mbti,
                            isSelected = isSelectMbti(mbti),
                            onSelected = {
                                onSelectMbti(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MbtiButton(
    mbtiItem: MbtiItem,
    isSelected: Boolean,
    onSelected: (MbtiItem) -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = if (isSelected) Gray500 else Color.Transparent,
                shape = FunchTheme.shapes.medium
            )
            .clip(FunchTheme.shapes.medium)
            .clickable(
                onClick = { onSelected(mbtiItem) },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = mbtiItem.name,
            color = if (isSelected) White else Gray400,
            style = FunchTheme.typography.sbt2
        )
    }
}

@Composable
private fun BooldTypeRow(
    onSelectBloodType: (Blood) -> Unit
) {
    val bloodTypes = Blood.entries.filterNot { it == Blood.IDLE }.map { it.type }
    var placeHolder by remember { mutableStateOf(bloodTypes[0]) }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    val buttonBounds = remember { mutableStateOf(Rect.Zero) }

    Row {
        FunchLargeLabel(text = ProfileLabel.BLOOD_TYPE.labelName)
        Box {
            FunchDropDownButton(
                placeHolder = placeHolder,
                onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
                isDropDownMenuExpanded = isDropDownMenuExpanded,
                indication = null,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    buttonBounds.value = coordinates.boundsInWindow()
                }
            )
            if (isDropDownMenuExpanded) {
                FunchDropDownMenu(
                    items = bloodTypes,
                    buttonBounds = buttonBounds.value,
                    onItemSelected = { bloodType ->
                        onSelectBloodType(Blood.of(bloodType))
                        placeHolder = bloodType
                        isDropDownMenuExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun SubwayRow(
    subwayStation: String,
    onSubwayStationChange: (String) -> Unit,
    isKeyboardVisible: (Boolean) -> Unit
) {
    val isError by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isFocused) {
        isKeyboardVisible(isFocused)
    }

    Row {
        FunchLargeLabel(text = ProfileLabel.SUBWAY.labelName)
        FunchIconTextField(
            value = subwayStation,
            onValueChange = onSubwayStationChange,
            hint = "가까운 지하철역 검색",
            isError = isError,
            errorText = "존재하지 않는 지하철역이에요",
            iconType = FunchIcon(
                resId = FunchIconAsset.Search.search_24,
                tint = Gray400,
                description = ""
            ),
            interactionSource = interactionSource,
            isFocus = isFocused,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@Composable
private fun BottomBar(
    backgroundColor: Color,
    onNavigateToHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(
                top = 16.dp,
                start = 20.dp,
                end = 20.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        FunchMainButton(
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            buttonType = FunchButtonType.Full,
            onClick = onNavigateToHome,
            text = "이제 매칭할래요!"
        )
    }
}

@Preview(
    showBackground = true,
    name = "CreateProfileScreen",
    widthDp = 360,
    heightDp = 1040
)
@Composable
private fun Preview1() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            CreateProfileRoute(
                onNavigateToHome = {},
                viewModel = CreateProfileViewModel()
            )
        }
    }
}
